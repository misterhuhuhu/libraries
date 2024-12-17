package mrwho.mybatisplus.util.sql;


import lombok.Getter;

@Getter
public enum ScopeParamEnum {
    AREA("ct_company_base_info","area_code"),
    COMPANY_ID("ct_company_base_info","area_code")
    ;
    
    private final String tableName;
    private final String paramColumnName;
    
    ScopeParamEnum(String tableName, String paramColumnName) {
        this.tableName=tableName;
        this.paramColumnName=paramColumnName;
        
    }
}
