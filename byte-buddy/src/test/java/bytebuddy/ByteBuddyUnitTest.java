package bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ByteBuddyUnitTest {

    private static final Logger logger = LoggerFactory.getLogger(ByteBuddyUnitTest.class);
    @Test
    public void 生成一个有tostring的类() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        
        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
                                                            .subclass(Object.class)//选择父类
                                                            .method(ElementMatchers.isToString())
                                                            .intercept(FixedValue.value("Hello World ByteBuddy!")).make();
        unloadedType.close();
        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();
        
        logger.info(dynamicType.getConstructor().newInstance().toString());
        
        
    }

    @Test
    public void givenFoo_whenRedefined_thenReturnFooRedefined() throws Exception {
        ByteBuddyAgent.install();
        new ByteBuddy().redefine(Foo.class).method(named("sayHelloFoo")).intercept(FixedValue.value("Hello Foo Redefined")).make().load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Foo f = new Foo();
        assertEquals(f.sayHelloFoo(), "Hello Foo Redefined");
    }

    @Test
    public void givenSayHelloFoo_whenMethodDelegation_thenSayHelloBar() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String r = new ByteBuddy().subclass(Foo.class).method(named("sayHelloFoo")
                                                                      .and(isDeclaredBy(Foo.class).and(returns(String.class))))
                           .intercept(MethodDelegation.to(Bar.class))
                           .make().load(getClass().getClassLoader())
                           .getLoaded()
                           .getConstructor().newInstance()
                .sayHelloFoo();
        
        logger.info(r);
    }
    

    @Test
    public void givenMethodName_whenDefineMethod_thenCreateMethod() throws Exception {
        Class<?> type = new ByteBuddy().subclass(Object.class).name("MyClassName").defineMethod("custom", String.class, Modifier.PUBLIC).intercept(MethodDelegation.to(Bar.class)).defineField("x", String.class, Modifier.PUBLIC).make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();

        Method m = type.getDeclaredMethod("custom", null);

        assertEquals(m.invoke(type.newInstance()), Bar.sayHelloBar());
        assertNotNull(type.getDeclaredField("x"));

    }

}
