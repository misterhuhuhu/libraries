package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

@TableName(value = "jpjg_info_gen")
public class JpjgInfoGen {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "company_name")
    private String companyName;

    @TableField(value = "area_name")
    private String areaName;

    @TableField(value = "address")
    private String address;

    @TableField(value = "people")
    private String people;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "zg_company_code")
    private String zgCompanyCode;

    @TableField(value = "area_code")
    private String areaCode;

    @TableField(value = "latitude")
    private BigDecimal latitude;

    @TableField(value = "longitude")
    private BigDecimal longitude;

    @TableField(value = "zw_code")
    private String zwCode;

    @TableField(value = "fire_code")
    private String fireCode;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return people
     */
    public String getPeople() {
        return people;
    }

    /**
     * @param people
     */
    public void setPeople(String people) {
        this.people = people;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return zg_company_code
     */
    public String getZgCompanyCode() {
        return zgCompanyCode;
    }

    /**
     * @param zgCompanyCode
     */
    public void setZgCompanyCode(String zgCompanyCode) {
        this.zgCompanyCode = zgCompanyCode;
    }

    /**
     * @return area_code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return zw_code
     */
    public String getZwCode() {
        return zwCode;
    }

    /**
     * @param zwCode
     */
    public void setZwCode(String zwCode) {
        this.zwCode = zwCode;
    }

    /**
     * @return fire_code
     */
    public String getFireCode() {
        return fireCode;
    }

    /**
     * @param fireCode
     */
    public void setFireCode(String fireCode) {
        this.fireCode = fireCode;
    }
}