package mrwho.mybatis.plugins;

import lombok.extern.slf4j.Slf4j;
import mrwho.mybatis.service.SomeService;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * 不能拦截非mybatis方法
 */
@Intercepts({
        @Signature(type = SomeService.class, method = "SomeMethod", args = {}),
//        @Signature(type = SomeService.class, method = "PrivateMethod", args = {}),
        @Signature(type = SomeService.class, method = "SomeMethodWithArgs", args = {String.class})
})
@Slf4j
public class Plugin3 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object arg = invocation.getArgs()[0];
        log.info(arg.toString());
        Object proceed = invocation.proceed();
        log.info(proceed.toString());
        return proceed;
    }
    
}
