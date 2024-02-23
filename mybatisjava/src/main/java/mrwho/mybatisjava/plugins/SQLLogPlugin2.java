package mrwho.mybatisjava.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class})
})
@Slf4j
public class SQLLogPlugin2 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object arg = invocation.getArgs()[0];
        Object proceed = invocation.proceed();
        
        return proceed;
    }
    
}
