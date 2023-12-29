package bytebuddy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class Foo2 {
    private static final Logger logger = LoggerFactory.getLogger(Foo2.class);
    
    /**
     * 被@RuntimeType标准的方法就是拦截方法此时方法签名或返回值可以与被拦截方法不一致,
     * bytebuddy会在运行期间给被指定注解修饰的方法参数进行赋值
     *
     * @param thisObj
     * @return
     */
    @RuntimeType
    public Object asdasd(
            @This //被拦截的实例
            Object thisObj,
            @Origin Method thisMethod,
            @AllArguments Object[] args,
            
            //父类  可以用确定的父类接收
            @Super Object thisSuper,
            @SuperCall Callable<?> superCall
    ) {
        
        logger.info("thisObj  {}", thisObj);
        logger.info("thisMethod.getName()  {}", thisMethod.getName());
        logger.info("Arrays.toString(args)  {}", Arrays.toString(args));
        logger.info("thisSuper  {}  ", thisSuper);
        Object call = null;
        try {
//            Object invoke = thisMethod.invoke(thisObj, args);//会导致递归调用
            call = superCall.call();
        } catch (Exception e) {
            logger.error(getClass().getName() + "{} :\n", e);
        }
        return call;
    }
    
}
