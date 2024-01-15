package mrwho.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.visitor.functions.Left;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
public class SQLParserTest {
    
    @Test
    void name() {
        String sqlInput = "select t1.*, t3.company_name as companyName, t2.name as risk_name\n" +
                                  "from ct_company_risk_chemical t1\n" +
                                  "         left join ct_company_risk_source t2 on t2.id = t1.risk_id\n" +
                                  "         left join ct_company_info  t3 on t3.id = t1.company_id\n" +
                                  "where risk_id = '1473563153827627010'\n" +
                                  "limit 10";
        
        MySqlStatementParser parser = new MySqlStatementParser(sqlInput);
        SQLSelectStatement sqlStatement = (SQLSelectStatement) parser.parseStatement();
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        
        sqlStatement.addWhere(SQLUtils.toMySqlExpr("id != ''"));//添加where条件
        
        sqlStatement.accept(visitor);
        Map<TableStat.Name, TableStat> tableStatMap = visitor.getTables();//获取原始表名
        Collection<TableStat.Column> columns = visitor.getColumns();//获取查询列名
        
        
        visitor.getConditions().forEach(k -> log.info("getColumn  {} , getOperator  {} ,  getValues  {}", k.getColumn(), k.getOperator(), k.getValues().stream().reduce((a, b) -> a + b.toString())));
        SQLSelect select = sqlStatement.getSelect();
        SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();
        SQLJoinTableSource tableSource = (SQLJoinTableSource) query.getFrom();
        
        log.info("Left  {}", tableSource.getLeft());
        log.info("Left.Left {}", ((SQLJoinTableSource) tableSource.getLeft()).getLeft());
        log.info("Right  {} ,alias {}", tableSource.getRight(), tableSource.getAlias());
        
        List<SQLSelectItem> selectList = query.getSelectList();
        selectList.forEach(k -> log.info("列名:  Alias   {}, Alias2  {}", k.getAlias(), k.getAlias2()));
        SQLJoinTableSource from = (SQLJoinTableSource) sqlStatement.getSelect().getQueryBlock().getFrom();
        
        log.info("-------开始打印表名及别名-------");
        printJoin(from);
        log.info("-------结束打印表名及别名-------");
        columns.forEach(k -> log.info(k.toString()));
        for (Map.Entry<TableStat.Name, TableStat> tableStatEntry : tableStatMap.entrySet()) {
            System.out.println("表名：" + tableStatEntry.getKey().getName());
            System.out.println("操作名：" + tableStatEntry.getValue());
        }
        log.info("sql = {}", sqlStatement.getSelect().toString());
        log.info("SQLUtils String   = {}", SQLUtils.toSQLString(Lists.newArrayList(sqlStatement), JdbcConstants.MYSQL));
    }
    private void  printJoin(SQLTableSource sqlTableSource){

        if(sqlTableSource instanceof SQLExprTableSource sqlExprTableSource){
            log.info("TableName : {}   , Alias : {} ", sqlExprTableSource.getTableName(),sqlExprTableSource.getAlias());
        }
        if(sqlTableSource instanceof SQLJoinTableSource sqlJoinTableSource){
            
            printJoin(sqlJoinTableSource.getLeft());
           
            
            printJoin(sqlJoinTableSource.getRight());
            log.info("JoinType {}",sqlJoinTableSource.getJoinType().name);
        }
    }
}
