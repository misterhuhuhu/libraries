package mrwho.mybatis.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.Objects;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "parameterize",
                args = {Statement.class})
})
@Slf4j
public class SQLLogPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object arg = invocation.getArgs()[0];
        Object proceed = invocation.proceed();
        if (Proxy.isProxyClass(arg.getClass())) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(arg);
            log.info(((PreparedStatementLogger) invocationHandler).getPreparedStatement().toString());
        } else {
            log.info(arg.toString());
        }
        return proceed;
    }
    
    /**
     *
     * Cause: java.lang.reflect.InaccessibleObjectException: Unable to make field protected java.lang.reflect.InvocationHandler java.lang.reflect.Proxy.h accessible: module java.base does not "opens java.lang.reflect" to unnamed module @31ef45e3
     * jdk8以上需要配置
     *             <plugin>
     *                 <groupId>org.apache.maven.plugins</groupId>
     *                 <artifactId>maven-surefire-plugin</artifactId>
     *                 <configuration>
     *                     <argLine>
     *                         --add-opens java.base/java.util=ALL-UNNAMED
     *                         --add-opens java.base/java.lang=ALL-UNNAMED
     *                         --add-opens java.base/java.nio=ALL-UNNAMED
     *                         --add-opens java.base/sun.nio.ch=ALL-UNNAMED
     *                     </argLine>
     *                 </configuration>
     *             </plugin>
     * --add-opens java.base/java.lang=ALL-UNNAMED
     * --add-opens java.base/java.util=ALL-UNNAMED
     * --add-opens java.base/java.nio=ALL-UNNAMED
     * --add-opens java.base/sun.nio.ch=ALL-UNNAMED
     * @param proxy
     * @return
     * @throws Exception
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
