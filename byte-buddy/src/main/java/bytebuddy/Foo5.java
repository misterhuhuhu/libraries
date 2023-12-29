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

public class Foo5 {
    private static final Logger logger = LoggerFactory.getLogger(Foo5.class);
    
    /**
     * 被@RuntimeType标准的方法就是拦截方法此时方法签名或返回值可以与被拦截方法不一致,
     * bytebuddy会在运行期间给被指定注解修饰的方法参数进行赋值
     *
     * @param thisObj
     * @return
     */
    @RuntimeType
    public Object asdasd(
            @Origin //被拦截的类
            Class<?> thisObj,
            @Origin Method thisMethod,
            @AllArguments Object[] args,
            
            @SuperCall Callable<?> superCall
    ) {
        
        logger.info("thisObj  {}", thisObj);
        logger.info("thisMethod.getName()  {}", thisMethod.getName());
        logger.info("Arrays.toString(args)  {}", Arrays.toString(args));
        Object call = null;
        try {
            call = superCall.call();
        } catch (Exception e) {
            logger.error(getClass().getName() + "{} :\n", e);
        }
        return call;
    }
    
}
