package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "blade_region_scratch")
@Data
public class Region {
    private static final long serialVersionUID = 1L;
    @TableId(value = "code", type = IdType.INPUT)
    private String code;
    
    @TableField(value = "`name`")
    private String name;
    
    @TableField(value = "parent_code")
    private String parentCode;
    
    @TableField(value = "ancestors")
    private String ancestors;
    
    @TableField(value = "tree_names")
    private String treeNames;
    
    @TableField(value = "province_code")
    private String provinceCode;
    
    @TableField(value = "province_name")
    private String provinceName;
    
    @TableField(value = "city_code")
    private String cityCode;
    
    @TableField(value = "city_name")
    private String cityName;
    
    @TableField(value = "district_code")
    private String districtCode;
    
    @TableField(value = "district_name")
    private String districtName;
    
    @TableField(value = "town_code")
    private String townCode;
    
    @TableField(value = "town_name")
    private String townName;
    
    @TableField(value = "village_code")
    private String villageCode;
    
    @TableField(value = "village_name")
    private String villageName;
    
    @TableField(value = "grid_code")
    private String gridCode;
    
    @TableField(value = "grid_name")
    private String gridName;
    
    @TableField(value = "region_level")
    private Integer regionLevel;
    
    @TableField(value = "sort")
    private Integer sort;
    
    @TableField(value = "remark")
    private String remark;
    
    @TableField(value = "tree_level")
    private Integer treeLevel;
    
    @TableField(value = "`status`")
    private Integer status;
    
    @TableField(value = "max_lng")
    @JsonIgnore
    private BigDecimal maxLng;
    
    @TableField(value = "max_lat")
    @JsonIgnore
    private BigDecimal maxLat;
    
    @TableField(value = "min_lng")
    @JsonIgnore
    private BigDecimal minLng;
    
    @JsonIgnore
    @TableField(value = "min_lat")
    private BigDecimal minLat;
    @JsonIgnore
    @TableField(value = "create_user")
    private String createUser;
    @JsonIgnore
    @TableField(value = "create_dept")
    private String createDept;
    
    @JsonIgnore
    @TableField(value = "create_time")
    private Date createTime;
    
    @TableField(value = "update_user")
    @JsonIgnore
    private String updateUser;
    @JsonIgnore
    @TableField(value = "update_time")
    private Date updateTime;
    
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    @JsonIgnore
    @TableField(value = "center_lng")
    private BigDecimal centerLng;
    @JsonIgnore
    @TableField(value = "center_lat")
    private BigDecimal centerLat;
    
    @JsonIgnore
    @TableField(exist = false)
    private String url;
    public static Region getDisReign(String parentCode, Region region) {
        Region copy = new Region();
        BeanUtils.copyProperties(region, copy);
        copy.setTreeLevel(2);
        copy.setRegionLevel(3);
        copy.setParentCode(parentCode);
        return copy;
    }
    
    
    public static Region getTownReign(String parentCode, Region region) {
        Region copy = new Region();
        BeanUtils.copyProperties(region, copy);
        copy.setTreeLevel(3);
        copy.setRegionLevel(4);
        copy.setParentCode(parentCode);
        return copy;
    }
    
    
    public static Region getVillageReign(String parentCode, Region region) {
        Region copy = new Region();
        BeanUtils.copyProperties(region, copy);
        copy.setTreeLevel(4);
        copy.setRegionLevel(5);
        copy.setParentCode(parentCode);
        return copy;
    }
}