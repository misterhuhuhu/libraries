package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
    * 联网教育单位基本信息总表
    */
@ToString
@TableName(value = "ct_company_base_info_jiaoyu")
public class CtCompanyBaseInfoJiaoyu {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属机构ID
     */
    @TableField(value = "organization_id")
    private String organizationId;

    /**
     * 单位名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 统一社会信用代码
     */
    @TableField(value = "unified_social_credit_code")
    private String unifiedSocialCreditCode;

    /**
     * 所属区域
     */
    @TableField(value = "area_code")
    private String areaCode;
    
    @TableField(value = "region_name")
    private String regionName;
    /**
     * 单位详址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联网状态，0-未联网，1-已联网
     */
    @TableField(value = "network_status")
    private String networkStatus;

    /**
     * 法人
     */
    @TableField(value = "corporator")
    private String corporator;

    /**
     * 法人电话
     */
    @TableField(value = "corporator_tel")
    private String corporatorTel;

    /**
     * 行业主管部门类型
     */
    @TableField(value = "industry_dept")
    private String industryDept;

    /**
     * 行业主管部门id
     */
    @TableField(value = "industry_dept_id")
    private String industryDeptId;

    /**
     * 消防主管部门
     */
    @TableField(value = "fire_manage_id")
    private String fireManageId;

    /**
     * 消防安全责任人
     */
    @TableField(value = "fire_safe_person")
    private String fireSafePerson;

    /**
     * 消防安全责任人电话
     */
    @TableField(value = "fire_safe_person_tel")
    private String fireSafePersonTel;

    /**
     * 编号
     */
    @TableField(value = "numbering")
    private String numbering;

    /**
     * 地理坐标纬度
     */
    @TableField(value = "latitude")
    private BigDecimal latitude;

    /**
     * 地理坐标经度
     */
    @TableField(value = "longitude")
    private BigDecimal longitude;

    /**
     * 政务运营中心
     */
    @TableField(value = "monitor_center_id")
    private String monitorCenterId;

    /**
     * 社会运营中心
     */
    @TableField(value = "social_center_id")
    private String socialCenterId;

    /**
     * 第三方平台的appId
     */
    @TableField(value = "third_id")
    private String thirdId;

    /**
     * 数据在第三方平台的id
     */
    @TableField(value = "third_data_id")
    private String thirdDataId;

    /**
     * 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-家庭用户，99-其他单位
     */
    @TableField(value = "monitor_type")
    private String monitorType;

    /**
     * 所属行业，cfs_data_industry_type
     */
    @TableField(value = "industry_type")
    private String industryType;

    /**
     * 维保公司
     */
    @TableField(value = "maintenance_unit_id")
    private String maintenanceUnitId;

    @TableField(value = "evaluation_level")
    private String evaluationLevel;

    /**
     * 所属网格
     */
    @TableField(value = "grid_code")
    private String gridCode;

    /**
     * 火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     */
    @TableField(value = "fire_danger")
    private String fireDanger;

    /**
     * 监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     */
    @TableField(value = "monitor_level")
    private Integer monitorLevel;

    /**
     * 单位属性，cfs_data_new_org_type
     */
    @TableField(value = "company_type")
    private String companyType;

    /**
     * 消防控制室电话
     */
    @TableField(value = "fire_manage_tel")
    private String fireManageTel;

    /**
     * 消防设施系统，cfs_data_fire_system_type
     */
    @TableField(value = "fire_systems")
    private String fireSystems;

    /**
     * 是否删除(0、未删除 1、已删除)
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 创建部门
     */
    @TableField(value = "create_dept")
    private String createDept;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 最新风险评估结果ID
     */
    @TableField(value = "risk_id")
    private String riskId;

    /**
     * 厨房面积
     */
    @TableField(value = "kitchen_area")
    private Double kitchenArea;

    /**
     * 法人身份证
     */
    @TableField(value = "corporator_id")
    private String corporatorId;

    /**
     * 单位层级
     */
    @TableField(value = "company_level")
    private String companyLevel;

    /**
     * 楼栋门牌号
     */
    @TableField(value = "house_number")
    private String houseNumber;

    /**
     * 学校类型
     */
    @TableField(value = "school_type")
    private Integer schoolType;
    
    public String getRegionName() {
        return regionName;
    }
    
    public CtCompanyBaseInfoJiaoyu setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }
    
    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取所属机构ID
     *
     * @return organization_id - 所属机构ID
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 设置所属机构ID
     *
     * @param organizationId 所属机构ID
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 获取单位名称
     *
     * @return name - 单位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置单位名称
     *
     * @param name 单位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return unified_social_credit_code - 统一社会信用代码
     */
    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param unifiedSocialCreditCode 统一社会信用代码
     */
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    /**
     * 获取所属区域
     *
     * @return area_code - 所属区域
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置所属区域
     *
     * @param areaCode 所属区域
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取单位详址
     *
     * @return address - 单位详址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置单位详址
     *
     * @param address 单位详址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联网状态，0-未联网，1-已联网
     *
     * @return network_status - 联网状态，0-未联网，1-已联网
     */
    public String getNetworkStatus() {
        return networkStatus;
    }

    /**
     * 设置联网状态，0-未联网，1-已联网
     *
     * @param networkStatus 联网状态，0-未联网，1-已联网
     */
    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    /**
     * 获取法人
     *
     * @return corporator - 法人
     */
    public String getCorporator() {
        return corporator;
    }

    /**
     * 设置法人
     *
     * @param corporator 法人
     */
    public void setCorporator(String corporator) {
        this.corporator = corporator;
    }

    /**
     * 获取法人电话
     *
     * @return corporator_tel - 法人电话
     */
    public String getCorporatorTel() {
        return corporatorTel;
    }

    /**
     * 设置法人电话
     *
     * @param corporatorTel 法人电话
     */
    public void setCorporatorTel(String corporatorTel) {
        this.corporatorTel = corporatorTel;
    }

    /**
     * 获取行业主管部门类型
     *
     * @return industry_dept - 行业主管部门类型
     */
    public String getIndustryDept() {
        return industryDept;
    }

    /**
     * 设置行业主管部门类型
     *
     * @param industryDept 行业主管部门类型
     */
    public void setIndustryDept(String industryDept) {
        this.industryDept = industryDept;
    }

    /**
     * 获取行业主管部门id
     *
     * @return industry_dept_id - 行业主管部门id
     */
    public String getIndustryDeptId() {
        return industryDeptId;
    }

    /**
     * 设置行业主管部门id
     *
     * @param industryDeptId 行业主管部门id
     */
    public void setIndustryDeptId(String industryDeptId) {
        this.industryDeptId = industryDeptId;
    }

    /**
     * 获取消防主管部门
     *
     * @return fire_manage_id - 消防主管部门
     */
    public String getFireManageId() {
        return fireManageId;
    }

    /**
     * 设置消防主管部门
     *
     * @param fireManageId 消防主管部门
     */
    public void setFireManageId(String fireManageId) {
        this.fireManageId = fireManageId;
    }

    /**
     * 获取消防安全责任人
     *
     * @return fire_safe_person - 消防安全责任人
     */
    public String getFireSafePerson() {
        return fireSafePerson;
    }

    /**
     * 设置消防安全责任人
     *
     * @param fireSafePerson 消防安全责任人
     */
    public void setFireSafePerson(String fireSafePerson) {
        this.fireSafePerson = fireSafePerson;
    }

    /**
     * 获取消防安全责任人电话
     *
     * @return fire_safe_person_tel - 消防安全责任人电话
     */
    public String getFireSafePersonTel() {
        return fireSafePersonTel;
    }

    /**
     * 设置消防安全责任人电话
     *
     * @param fireSafePersonTel 消防安全责任人电话
     */
    public void setFireSafePersonTel(String fireSafePersonTel) {
        this.fireSafePersonTel = fireSafePersonTel;
    }

    /**
     * 获取编号
     *
     * @return numbering - 编号
     */
    public String getNumbering() {
        return numbering;
    }

    /**
     * 设置编号
     *
     * @param numbering 编号
     */
    public void setNumbering(String numbering) {
        this.numbering = numbering;
    }

    /**
     * 获取地理坐标纬度
     *
     * @return latitude - 地理坐标纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置地理坐标纬度
     *
     * @param latitude 地理坐标纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取地理坐标经度
     *
     * @return longitude - 地理坐标经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 设置地理坐标经度
     *
     * @param longitude 地理坐标经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取政务运营中心
     *
     * @return monitor_center_id - 政务运营中心
     */
    public String getMonitorCenterId() {
        return monitorCenterId;
    }

    /**
     * 设置政务运营中心
     *
     * @param monitorCenterId 政务运营中心
     */
    public void setMonitorCenterId(String monitorCenterId) {
        this.monitorCenterId = monitorCenterId;
    }

    /**
     * 获取社会运营中心
     *
     * @return social_center_id - 社会运营中心
     */
    public String getSocialCenterId() {
        return socialCenterId;
    }

    /**
     * 设置社会运营中心
     *
     * @param socialCenterId 社会运营中心
     */
    public void setSocialCenterId(String socialCenterId) {
        this.socialCenterId = socialCenterId;
    }

    /**
     * 获取第三方平台的appId
     *
     * @return third_id - 第三方平台的appId
     */
    public String getThirdId() {
        return thirdId;
    }

    /**
     * 设置第三方平台的appId
     *
     * @param thirdId 第三方平台的appId
     */
    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 获取数据在第三方平台的id
     *
     * @return third_data_id - 数据在第三方平台的id
     */
    public String getThirdDataId() {
        return thirdDataId;
    }

    /**
     * 设置数据在第三方平台的id
     *
     * @param thirdDataId 数据在第三方平台的id
     */
    public void setThirdDataId(String thirdDataId) {
        this.thirdDataId = thirdDataId;
    }

    /**
     * 获取监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-家庭用户，99-其他单位
     *
     * @return monitor_type - 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-家庭用户，99-其他单位
     */
    public String getMonitorType() {
        return monitorType;
    }

    /**
     * 设置监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-家庭用户，99-其他单位
     *
     * @param monitorType 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-家庭用户，99-其他单位
     */
    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    /**
     * 获取所属行业，cfs_data_industry_type
     *
     * @return industry_type - 所属行业，cfs_data_industry_type
     */
    public String getIndustryType() {
        return industryType;
    }

    /**
     * 设置所属行业，cfs_data_industry_type
     *
     * @param industryType 所属行业，cfs_data_industry_type
     */
    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    /**
     * 获取维保公司
     *
     * @return maintenance_unit_id - 维保公司
     */
    public String getMaintenanceUnitId() {
        return maintenanceUnitId;
    }

    /**
     * 设置维保公司
     *
     * @param maintenanceUnitId 维保公司
     */
    public void setMaintenanceUnitId(String maintenanceUnitId) {
        this.maintenanceUnitId = maintenanceUnitId;
    }

    /**
     * @return evaluation_level
     */
    public String getEvaluationLevel() {
        return evaluationLevel;
    }

    /**
     * @param evaluationLevel
     */
    public void setEvaluationLevel(String evaluationLevel) {
        this.evaluationLevel = evaluationLevel;
    }

    /**
     * 获取所属网格
     *
     * @return grid_code - 所属网格
     */
    public String getGridCode() {
        return gridCode;
    }

    /**
     * 设置所属网格
     *
     * @param gridCode 所属网格
     */
    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    /**
     * 获取火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     *
     * @return fire_danger - 火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     */
    public String getFireDanger() {
        return fireDanger;
    }

    /**
     * 设置火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     *
     * @param fireDanger 火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     */
    public void setFireDanger(String fireDanger) {
        this.fireDanger = fireDanger;
    }

    /**
     * 获取监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     *
     * @return monitor_level - 监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     */
    public Integer getMonitorLevel() {
        return monitorLevel;
    }

    /**
     * 设置监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     *
     * @param monitorLevel 监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     */
    public void setMonitorLevel(Integer monitorLevel) {
        this.monitorLevel = monitorLevel;
    }

    /**
     * 获取单位属性，cfs_data_new_org_type
     *
     * @return company_type - 单位属性，cfs_data_new_org_type
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置单位属性，cfs_data_new_org_type
     *
     * @param companyType 单位属性，cfs_data_new_org_type
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取消防控制室电话
     *
     * @return fire_manage_tel - 消防控制室电话
     */
    public String getFireManageTel() {
        return fireManageTel;
    }

    /**
     * 设置消防控制室电话
     *
     * @param fireManageTel 消防控制室电话
     */
    public void setFireManageTel(String fireManageTel) {
        this.fireManageTel = fireManageTel;
    }

    /**
     * 获取消防设施系统，cfs_data_fire_system_type
     *
     * @return fire_systems - 消防设施系统，cfs_data_fire_system_type
     */
    public String getFireSystems() {
        return fireSystems;
    }

    /**
     * 设置消防设施系统，cfs_data_fire_system_type
     *
     * @param fireSystems 消防设施系统，cfs_data_fire_system_type
     */
    public void setFireSystems(String fireSystems) {
        this.fireSystems = fireSystems;
    }

    /**
     * 获取是否删除(0、未删除 1、已删除)
     *
     * @return is_deleted - 是否删除(0、未删除 1、已删除)
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除(0、未删除 1、已删除)
     *
     * @param isDeleted 是否删除(0、未删除 1、已删除)
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建部门
     *
     * @return create_dept - 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 设置创建部门
     *
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取最新风险评估结果ID
     *
     * @return risk_id - 最新风险评估结果ID
     */
    public String getRiskId() {
        return riskId;
    }

    /**
     * 设置最新风险评估结果ID
     *
     * @param riskId 最新风险评估结果ID
     */
    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    /**
     * 获取厨房面积
     *
     * @return kitchen_area - 厨房面积
     */
    public Double getKitchenArea() {
        return kitchenArea;
    }

    /**
     * 设置厨房面积
     *
     * @param kitchenArea 厨房面积
     */
    public void setKitchenArea(Double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    /**
     * 获取法人身份证
     *
     * @return corporator_id - 法人身份证
     */
    public String getCorporatorId() {
        return corporatorId;
    }

    /**
     * 设置法人身份证
     *
     * @param corporatorId 法人身份证
     */
    public void setCorporatorId(String corporatorId) {
        this.corporatorId = corporatorId;
    }

    /**
     * 获取单位层级
     *
     * @return company_level - 单位层级
     */
    public String getCompanyLevel() {
        return companyLevel;
    }

    /**
     * 设置单位层级
     *
     * @param companyLevel 单位层级
     */
    public void setCompanyLevel(String companyLevel) {
        this.companyLevel = companyLevel;
    }

    /**
     * 获取楼栋门牌号
     *
     * @return house_number - 楼栋门牌号
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * 设置楼栋门牌号
     *
     * @param houseNumber 楼栋门牌号
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * 获取学校类型
     *
     * @return school_type - 学校类型
     */
    public Integer getSchoolType() {
        return schoolType;
    }

    /**
     * 设置学校类型
     *
     * @param schoolType 学校类型
     */
    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }
}