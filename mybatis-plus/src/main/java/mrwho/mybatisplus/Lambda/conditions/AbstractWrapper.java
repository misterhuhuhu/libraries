package mrwho.mybatisplus.Lambda.conditions;


import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.ColumnSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import lombok.Getter;
import mrwho.mybatisplus.Lambda.conditions.interfaces.Compare;
import mrwho.mybatisplus.Lambda.conditions.segments.MergeSegments;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.baomidou.mybatisplus.core.enums.SqlKeyword.EQ;

public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> implements Compare<Children, R> {
    protected final Children typedThis = (Children) this;
    protected MergeSegments expression;
    protected SharedString paramAlias;
    protected AtomicInteger paramNameSeq;
    @Getter
    protected Map<String, Object> paramNameValuePairs;
    @Override
    public Children eq(boolean condition, R column, Object val) {
        return addCondition(condition, column, EQ, val);
    }
    protected Children addCondition(boolean condition, R column, SqlKeyword sqlKeyword, Object val) {
        appendSqlSegments(columnToSqlSegment(column), sqlKeyword,
                () -> formatParam(null, val));
        return typedThis;
    }
    protected final String formatParam(String mapping, Object param) {
        final String genParamName = Constants.WRAPPER_PARAM + paramNameSeq.incrementAndGet();
        final String paramStr = getParamAlias() + Constants.WRAPPER_PARAM_MIDDLE + genParamName;
        paramNameValuePairs.put(genParamName, param);
        return SqlScriptUtils.safeParam(paramStr, mapping);
    }
    public String getParamAlias() {
        return paramAlias == null ? Constants.WRAPPER : paramAlias.getStringValue();
    }
    /**
     * 获取 columnName
     */
    protected final ColumnSegment columnToSqlSegment(R column) {
        return () -> (String) column;
    }
    
    /**
     * 获取 columnName
     */
    protected String columnToString(R column) {
        return (String) column;
    }
    protected void appendSqlSegments(ISqlSegment... sqlSegments) {
        expression.add(sqlSegments);
    }
}
