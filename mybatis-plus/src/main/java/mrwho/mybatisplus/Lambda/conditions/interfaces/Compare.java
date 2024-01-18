package mrwho.mybatisplus.Lambda.conditions.interfaces;

import java.io.Serializable;

public interface Compare<Children, R> extends Serializable {
    default Children eq(R column, Object val) {
        return eq(true, column, val);
    }
    Children eq(boolean condition, R column, Object val);
}
