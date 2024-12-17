//package mrwho.mybatisplus.util.sql;
//
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import net.sf.jsqlparser.expression.Alias;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
//import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.schema.Table;
//import net.sf.jsqlparser.statement.select.FromItem;
//import net.sf.jsqlparser.statement.select.Join;
//
//import net.sf.jsqlparser.statement.select.PlainSelect;
//import net.sf.jsqlparser.statement.select.SelectItem;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.util.ObjectUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataAuthSQLUtil {
//    /**
//     * 1.@DateScope 内容为空,根据默认格式
//     * 2.@DateScope 有值,根据设置的列名(@DataScope(areaColumn = "c.area_code", deptColumn = "c.social_center_id", companyColumn = "c.id")
//     * 1.将[权限列名]分割成[别名],[列名]
//     * 2.加[条件]
//     * 1.[别名]在from后
//     * 把[条件]加在 from [table][别名]上
//     * 2.[别名]在join后
//     * 把[条件]加在 join [table][别名]上
//     * 1.子查询只看[别名]是否=[权限列名],加在where上
//     * 2.table查询 =[权限列名],
//     *
//     * @param selectExpression
//     * @return
//     */
//    private ArrayList<Expression> expressionArrayList = new ArrayList<>();// from table alias;join table alias
//    static String areaColumn = "c.area_code";
//    static String deptColumn = "cb.social_center_id";
//    static String companyColumn = "cb.id";
//
//    private static SelectItem<?> columnToSelectExpression(String column) {
//
//        boolean contains = column.contains(StringPool.DOT);
//        if (contains) {
//            String[] split = StringUtils.split(column, StringPool.DOT);
//            return new SelectItem<>(new Column(new Table(split[0]), split[1]));
//        } else {
//            return new SelectItem<>(new Column(column));
//        }
//    }
//
//    public static void main(String[] args) {
//        SelectItem<?> selectItem = columnToSelectExpression(areaColumn);
//        System.out.println(selectItem);
//    }
//
//    public static Expression getExpressionWithAuth(Expression selectExpression) {
//        if (ObjectUtils.isEmpty(selectExpression)) {
//            return null;
//        }
//        if (selectExpression instanceof PlainSelect) {
//            Expression where = ((PlainSelect) selectExpression).getWhere();//null equalTo AndExpression
//            FromItem fromItem = ((PlainSelect) selectExpression).getFromItem();//Table ParenthesedSelect
//            Alias alias = fromItem.getAlias();
//            List<Join> joins = ((PlainSelect) selectExpression).getJoins();
//            joins.forEach(joinItem -> joinItem.getFromItem());
//        }
//        if (selectExpression instanceof ParenthesedSelect) {
//            PlainSelect plainSelect = ((ParenthesedSelect) selectExpression).getPlainSelect();
//            return getExpressionWithAuth(plainSelect);
//        }
//        throw new RuntimeException("非法select语句" + selectExpression);
//    }
//
//    private static Expression tableSetWhereClaus(Expression selectExpression, Expression whereExpression) {
//        if (selectExpression instanceof PlainSelect) {
//            PlainSelect plainSelect = (PlainSelect) selectExpression;
//            FromItem fromItem = plainSelect.getFromItem();
//            if (fromItem instanceof Table) {
//                plainSelect.setWhere(plainSelect.getWhere());
//            }
//        }
//        return null;
//    }
//
//    private static Expression setWhere(Expression originalWhere, Expression newWhere) {
//        if (ObjectUtils.isEmpty(originalWhere)) {
//            return newWhere;
//        }
//        if (originalWhere instanceof EqualsTo || originalWhere instanceof AndExpression) {
//            //一个条件
//            return new AndExpression(originalWhere, newWhere);
//        }
//        return newWhere;
//    }
//}
