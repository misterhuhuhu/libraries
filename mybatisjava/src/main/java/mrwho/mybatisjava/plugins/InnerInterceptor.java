//package mrwho.mybatisjava.plugins;
//
//import org.apache.ibatis.executor.BatchExecutor;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.ReuseExecutor;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.Properties;
//
//public interface  InnerInterceptor {
//    /**
//     * 判断是否执行 {@link Executor#query(MappedStatement, Object, RowBounds, ResultHandler, CacheKey, BoundSql)}
//     * <p>
//     * 如果不执行query操作,则返回 {@link Collections#emptyList()}
//     *
//     * @param executor      Executor(可能是代理对象)
//     * @param ms            MappedStatement
//     * @param parameter     parameter
//     * @param rowBounds     rowBounds
//     * @param resultHandler resultHandler
//     * @param boundSql      boundSql
//     * @return 新的 boundSql
//     */
//    default boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        return true;
//    }
//
//    /**
//     * {@link Executor#query(MappedStatement, Object, RowBounds, ResultHandler, CacheKey, BoundSql)} 操作前置处理
//     * <p>
//     * 改改sql啥的
//     *
//     * @param executor      Executor(可能是代理对象)
//     * @param ms            MappedStatement
//     * @param parameter     parameter
//     * @param rowBounds     rowBounds
//     * @param resultHandler resultHandler
//     * @param boundSql      boundSql
//     */
//    default void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        // do nothing
//    }
//
//    /**
//     * 判断是否执行 {@link Executor#update(MappedStatement, Object)}
//     * <p>
//     * 如果不执行update操作,则影响行数的值为 -1
//     *
//     * @param executor  Executor(可能是代理对象)
//     * @param ms        MappedStatement
//     * @param parameter parameter
//     */
//    default boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
//        return true;
//    }
//
//    /**
//     * {@link Executor#update(MappedStatement, Object)} 操作前置处理
//     * <p>
//     * 改改sql啥的
//     *
//     * @param executor  Executor(可能是代理对象)
//     * @param ms        MappedStatement
//     * @param parameter parameter
//     */
//    default void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
//        // do nothing
//    }
//
//    /**
//     * {@link StatementHandler#prepare(Connection, Integer)} 操作前置处理
//     * <p>
//     * 改改sql啥的
//     *
//     * @param sh                 StatementHandler(可能是代理对象)
//     * @param connection         Connection
//     * @param transactionTimeout transactionTimeout
//     */
//    default void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
//        // do nothing
//    }
//
//    /**
//     * {@link StatementHandler#getBoundSql()} 操作前置处理
//     * <p>
//     * 只有 {@link BatchExecutor} 和 {@link ReuseExecutor} 才会调用到这个方法
//     *
//     * @param sh StatementHandler(可能是代理对象)
//     */
//    default void beforeGetBoundSql(StatementHandler sh) {
//        // do nothing
//    }
//
//    default void setProperties(Properties properties) {
//        // do nothing
//    }
//}
