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


public class Foo4 {
    private static final Logger logger = LoggerFactory.getLogger(Foo4.class);

    @RuntimeType
    public void asdasd(
            @This //被拦截的实例
            Object thisObj,
//            @Origin Method thisMethod, //不能有
            @AllArguments Object[] args,
            //父类
            @Super Object thisSuper
//            @SuperCall Callable<?> superCall
    ) throws Exception {
        
        logger.info("thisObj  {}", thisObj);
//        logger.info("thisMethod.getName()  {}", thisMethod.getName());
        logger.info("Arrays.toString(args)  {}", Arrays.toString(args));
        logger.info("thisSuper  {}  ", thisSuper);
//        return superCall.call();
    }
    
}
