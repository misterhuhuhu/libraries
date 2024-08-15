package mrwho.mybatisplus.config;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
@Slf4j
public class Plugin3 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        Object arg = invocation.getArgs()[0];
//        log.info(arg.toString());
//        Object proceed = invocation.proceed();
//        log.info(proceed.toString());
//        return proceed;
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement=null;
        if (ArrayUtils.isNotEmpty(args)) {
            mappedStatement = (MappedStatement) args[0];
        }else {
            return invocation.proceed();
        }
        // 先判断是不是SELECT操作
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType() || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        if (args.length == 4) {
            //4 个参数时
            boundSql = mappedStatement.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        //获取到原始sql语句
        String sql = boundSql.getSql();
        //获取到原始sql语句
        log.info(sql);
        Statement statement = CCJSqlParserUtil.parse(sql);
        return invocation.proceed();
        
    }
    
}
