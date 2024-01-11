package bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;


public class ByteBuddyUnitTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ByteBuddyUnitTest.class);
    
    private static final String file = Objects.requireNonNull(ByteBuddyUnitTest.class.getClassLoader().getResource("./")).getPath();
    
    @Test
    public void 运行时生成一个有tostring的类() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        
        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
//                                                            .with(new NamingStrategy.PrefixingRandom("test")).with(new NamingStrategy.SuffixingRandom("manual"))//指定动态class名后缀（每次都不一样）
                                                            .subclass(Object.class)//选择Object为父类
//                                                            .name("bytebuddy.ToString")//指定class名，固定为此class名
                                                            .method(ElementMatchers.isToString())//选择 Object.toString()
                                                            
                                                            .intercept(FixedValue.value("Hello World ByteBuddy!"))//使用 intercept（） 方法,提供了 toString（） 的实现并返回一个固定值  (插装)
                                                            .make();//make（） 方法触发新类的生成
        unloadedType.close();
        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();
        
        logger.info(dynamicType.getConstructor().newInstance().toString());
        logger.info(dynamicType.getName());
//        unloadedType.saveIn(new File(file));//写入到文件
//        unloadedType.inject(new File(""));//注入到某个jar包
    }
    
    @Test
    public void 重新定义现有类不保留原方法() throws Exception {
        ByteBuddyAgent.install();
        logger.info(Foo.class.getName());
        new ByteBuddy()
                .redefine(Foo.class)
                .method(ElementMatchers.named("sayHelloFoo"))
                // StubMethod.INSTANCE 代表清空方法体
                .intercept(FixedValue.value("Hello Foo Redefined"))
                .make()
                .load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Foo f = new Foo();
        logger.info(f.sayHelloFoo());
        logger.info(Foo.class.getName());
    }
    
    
    @Test
    public void 方法委托和自定义逻辑_将Foo的sayHelloFoo_委托给Bar的SayHelloBar() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy().subclass(Foo.class)
                                                     .method(ElementMatchers.named("sayHelloFoo").and(ElementMatchers.isDeclaredBy(Foo.class).and(ElementMatchers.returns(String.class)))// 方法签名、返回类型、方法名称和注释选择匹配的方法
                                                     )
                                                     .intercept(MethodDelegation.to(Bar.class)
//                           .intercept(MethodDelegation.to(new Bar())// @BindingPriority 定义绑定顺序 数字越大越优先 1.如果没有实例则方法必须是静态的,2.如果有则直接写入new class(),有实例时成员方法,不是静态方法(这个方法会使用实例,不推荐)
                                                     )
                                                     .make();
        String r = unloaded.load(getClass().getClassLoader()).getLoaded().getConstructor().newInstance().sayHelloFoo();
        unloaded.saveIn(new File(file));
        logger.info(r);
    }
    
    @Test
    public void 测试rebase() throws IOException {
        //rebase 保留所有方法\属性\包名
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy().rebase(Foo.class)
                                                     .defineMethod("custom", String.class, Modifier.PUBLIC | Modifier.STATIC)//定义一个方法 custom，它返回一个 String 并具有一个public修饰符 static修饰符
                                                     .intercept(MethodDelegation.to(Bar.class))//截获对它的调用并将其委托给Bar.class
                                                     .defineField("x", String.class, Modifier.PUBLIC)//定义 public String x;
                                                     .make();
        unloaded.saveIn(new File(file));
        
    }
    
    @Test
    public void 获取委托实例() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy().subclass(Foo.class)
                                                     .name("a.b.c")
                                                     .method(ElementMatchers.named("sayHelloFoo").and(ElementMatchers.isDeclaredBy(Foo.class).and(ElementMatchers.returns(String.class))))
                                                     .intercept(MethodDelegation.to(new Foo2()))
                                                     .make();
        Foo foo = unloaded.load(getClass().getClassLoader()).getLoaded().getConstructor().newInstance();
        String s = foo.sayHelloFoo();
        logger.info("return {}", s);
        /**
         * INFO bytebuddy.Foo2 -- thisObj  a.b.c@2072acb2
         * INFO bytebuddy.Foo2 -- thisMethod.getName()  sayHelloFoo
         * INFO bytebuddy.Foo2 -- Arrays.toString(args)  []
         * INFO bytebuddy.Foo2 -- thisSuper  a.b.c@2072acb2
         */
        unloaded.saveIn(new File(file));
        
    }
    
    @Test
    public void 动态修改入参() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy().subclass(Foo.class)
                                                     .name("a.b.c")
                                                     //找到有一个参数的方法
                                                     .method(ElementMatchers.named("sayHelloFoo").and(ElementMatchers.isDeclaredBy(Foo.class).and(ElementMatchers.takesArguments(1)).and(ElementMatchers.returns(String.class))))
                                                     /**
                                                      * 1. 定义  Callable.class
                                                      * 2. 使用 @Morph 注释 supercall
                                                      * 3. 调用 withBinders  intercept(MethodDelegation.withDefaultConfiguration().withBinders(Morph.Binder.install(Foo3.Callable.class))
                                                      */
                                                     .intercept(MethodDelegation.withDefaultConfiguration().withBinders(Morph.Binder.install(Foo3.Callable.class)).to(new Foo3()))
                                                     .make();
        Foo foo = unloaded.load(getClass().getClassLoader()).getLoaded().getConstructor().newInstance();
        String s = foo.sayHelloFoo(100);
        logger.info("return {}", s);
        /**
         * INFO bytebuddy.Foo3 -- thisObj  a.b.c@5f6722d3
         * INFO bytebuddy.Foo3 -- thisMethod.getName()  sayHelloFoo
         * INFO bytebuddy.Foo3 -- Arrays.toString(args)  [100]
         * INFO bytebuddy.Foo3 -- thisSuper  a.b.c@5f6722d3
         */
        unloaded.saveIn(new File(file));
        
    }
    
    @Test
    public void 拦截构造() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy().subclass(Foo.class)
                                                     .name("a.b.cObj")
                                                     //找到有一个参数的方法
                                                     .constructor(ElementMatchers.any())
                                                     .intercept(SuperMethodCall.INSTANCE.andThen(MethodDelegation.to(new Foo4())))//****必须在调用委托前调用父类构造****
                                                     .make();
        
        
        Foo foo = unloaded.load(getClass().getClassLoader()).getLoaded().getConstructor().newInstance();
        /**
         * Foo 构造
         * 10:45:44.017 [main] INFO bytebuddy.Foo4 -- thisObj  a.b.cObj@686449f9
         * 10:45:44.023 [main] INFO bytebuddy.Foo4 -- Arrays.toString(args)  []
         * 10:45:44.024 [main] INFO bytebuddy.Foo4 -- thisSuper  a.b.cObj@686449f9
         */
        unloaded.saveIn(new File(file));
    }
    
    @Test
    public void 拦截静态() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy()
                                                     //不能使用.subclass() 因为静态方法不会被继承
                                                     //不能使用 redefine 因为不保留原方法
                                                     
                                                     .rebase(Foo.class)
                                                     .name("t.Foo")
                                                     .method(ElementMatchers.named("sayFoo").and(ElementMatchers.isStatic()))
                                                     .intercept((MethodDelegation.to(new Foo5())))
                                                     .make();
        Class<? extends Foo> loaded = unloaded.load(getClass().getClassLoader()).getLoaded();
        Object s = loaded.getMethod("sayFoo").invoke(Foo.class);
        logger.info("return {}", s);
        /**
         * INFO bytebuddy.Foo5 -- thisObj  class t.Foo
         * INFO bytebuddy.Foo5 -- thisMethod.getName()  sayFoo
         * INFO bytebuddy.Foo5 -- Arrays.toString(args)  []
         * INFO bytebuddy.ByteBuddyUnitTest -- return Foo in Foo!
         */
        unloaded.saveIn(new File(file));
    }
    
    @Test
    public void 定义方法和字段定义() throws Exception {
        DynamicType.Unloaded<Object> unloaded = new ByteBuddy().subclass(Object.class)// Object.class 的子类
//                                                    .name("MyClassName")//创建了一个名为 MyClassName 的类
                                                        .defineMethod("custom", String.class, Modifier.PUBLIC | Modifier.STATIC)//定义一个方法 custom，它返回一个 String 并具有一个public修饰符 static修饰符
                                                        .intercept(MethodDelegation.to(new Bar()))//截获对它的调用并将其委托给Bar.class
                                                        .defineField("x", String.class, Modifier.PUBLIC)//定义 public String x;
                                                        .make();
        Class<?> type = unloaded
                                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();
        
        Method m = type.getDeclaredMethod("custom");
        unloaded.saveIn(new File(file));
        Object invoke = m.invoke(type.getConstructor().newInstance());
        logger.info(invoke.toString());
    }
    
    /**
     * 如果  类 已加载
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    void class_already_loaded() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DynamicType.Unloaded<Foo> unloaded = new ByteBuddy()
                                                     //不能使用.subclass() 因为静态方法不会被继承
                                                     //不能使用 redefine 因为不保留原方法
                                                     
                                                     .rebase(Foo.class)
                                                     .name("t.Foo")
                                                     .method(ElementMatchers.named("sayFoo").and(ElementMatchers.isStatic()))
                                                     .intercept((MethodDelegation.to(new Foo5())))
                                                     .make();
        // ClassLoadingStrategy.Default.CHILD_FIRST  子类加载器优先,解决 java.lang.IllegalStateException: Class already loaded: class t.Foo
        Class<? extends Foo> loaded = unloaded.load(getClass().getClassLoader(),ClassLoadingStrategy.Default.CHILD_FIRST).getLoaded();
        Object s = loaded.getMethod("sayFoo").invoke(Foo.class);
    }
    
    /**
     * 拦截指定jar包的指定方法
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    @Test
    void 自定义类的加载classLoader() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        
        //从指定jar加载
        ClassFileLocator jarClassFileLocator = ClassFileLocator.ForJarFile.of(new File("D:\\dev_soft\\apache-maven-3.9.4\\localRepository\\org\\springframework\\spring-beans\\6.1.2\\spring-beans-6.1.2.jar"));
       //加载文件夹中指定.class文件
//        ClassFileLocator.ForFolder forFolder = new ClassFileLocator.ForFolder(new File(""));
        ClassFileLocator systemLoader = ClassFileLocator.ForClassLoader.ofSystemLoader();
        
        ClassFileLocator.Compound compound = new ClassFileLocator.Compound(jarClassFileLocator, systemLoader);
        TypePool typePool = TypePool.Default.of(compound);
        
        // 不会加载
        TypeDescription typeDefinition = typePool.describe("org.springframework.beans.factory.support.RootBeanDefinition").resolve();
        
        DynamicType.Unloaded<Object> unloaded = new ByteBuddy()
                        
                                                     .redefine(typeDefinition,compound)
                                                     .method(ElementMatchers.named("getTargetType"))
                                                     .intercept((MethodDelegation.to(new Foo2())))
                                                     .make();
        unloaded.saveIn(new File(file));

    }
    
}
