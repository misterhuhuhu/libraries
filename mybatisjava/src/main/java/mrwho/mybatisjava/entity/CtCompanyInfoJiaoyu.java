package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

/**
    * 联网单位信息表
    */
@TableName(value = "ct_company_info_jiaoyu")
public class CtCompanyInfoJiaoyu {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属机构ID
     */
    @TableField(value = "organization_id")
    private String organizationId;

    /**
     * 联网单位名称
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 行业主管部门
     */
    @TableField(value = "industry_dept")
    private String industryDept;

    /**
     * 行业主管部门id
     */
    @TableField(value = "industry_dept_id")
    private String industryDeptId;

    /**
     * 所属行业，cfs_data_industry_type
     */
    @TableField(value = "industry_type")
    private String industryType;

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
    
    public String getRegionName() {
        return regionName;
    }
    
    public CtCompanyInfoJiaoyu setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }
    
    /**
     * 单位详址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 所属网格
     */
    @TableField(value = "grid_code")
    private String gridCode;

    /**
     * 消防主管部门
     */
    @TableField(value = "fire_manage_id")
    private String fireManageId;

    /**
     * 主管消防监督员
     */
    @TableField(value = "charge_fire_supervisor")
    private String chargeFireSupervisor;

    /**
     * 协管消防监督员
     */
    @TableField(value = "assist_fire_supervisor")
    private String assistFireSupervisor;

    /**
     * 单位属性，cfs_data_new_org_type
     */
    @TableField(value = "company_type")
    private String companyType;

    /**
     * 消防设施系统，cfs_data_fire_system_type
     */
    @TableField(value = "fire_systems")
    private String fireSystems;

    /**
     * 施工单位
     */
    @TableField(value = "construction_unit")
    private String constructionUnit;

    /**
     * 检测公司
     */
    @TableField(value = "check_unit")
    private String checkUnit;

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
     * 维保公司
     */
    @TableField(value = "maintenance_unit_id")
    private String maintenanceUnitId;

    /**
     * 地图属性，0-百度地图，1-三维模式，2-2.5D地图
     */
    @TableField(value = "map_type")
    private Integer mapType;

    /**
     * 单位图标
     */
    @TableField(value = "company_icon")
    private String companyIcon;

    /**
     * 成立时间
     */
    @TableField(value = "org_license_time")
    private Date orgLicenseTime;

    /**
     * 邮政编码
     */
    @TableField(value = "postal_code")
    private String postalCode;

    /**
     * 职工总人数
     */
    @TableField(value = "work_num")
    private Integer workNum;

    /**
     * 固定资产（万）
     */
    @TableField(value = "fixed_assets")
    private Double fixedAssets;

    /**
     * 经济所有制
     */
    @TableField(value = "economic_ownership")
    private String economicOwnership;

    /**
     * 单位占地面积（平方米）
     */
    @TableField(value = "unit_area")
    private Double unitArea;

    /**
     * 总建筑面积（平方米）
     */
    @TableField(value = "total_building_area")
    private Double totalBuildingArea;

    /**
     * 监管等级，1-非消防安全重点单位，2-支队列管消防安全重点单位，3-大队列管消防安全重点单位，4-派出所列管消防安全重点单位
     */
    @TableField(value = "monitor_level")
    private Integer monitorLevel;

    /**
     * 消防控制室电话
     */
    @TableField(value = "fire_manage_tel")
    private String fireManageTel;

    /**
     * 所属救援部门
     */
    @TableField(value = "rescue_department")
    private String rescueDepartment;

    /**
     * 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-个人用户，99-其他单位
     */
    @TableField(value = "monitor_type")
    private String monitorType;

    /**
     * 联网状态，0-未联网，1-已联网
     */
    @TableField(value = "network_status")
    private String networkStatus;

    /**
     * 入网日期
     */
    @TableField(value = "access_date")
    private Date accessDate;

    /**
     * 火灾危险等级，1-高危风险，2-中危风险，3-低危风险
     */
    @TableField(value = "fire_danger")
    private String fireDanger;

    /**
     * 防火监督员
     */
    @TableField(value = "fire_supervisor")
    private String fireSupervisor;

    /**
     * 防火监督员电话
     */
    @TableField(value = "fire_supervisor_tel")
    private String fireSupervisorTel;

    /**
     * 营业时最大人数
     */
    @TableField(value = "max_mum_people")
    private String maxMumPeople;

    /**
     * 是否有自动消防设施，0-无，1-有
     */
    @TableField(value = "is_automatic_fire")
    private String isAutomaticFire;

    /**
     * 单位专职(志愿)消防员数
     */
    @TableField(value = "fire_fighters_mum")
    private String fireFightersMum;

    /**
     * 避难层总面积（平方米）
     */
    @TableField(value = "floor_mum")
    private String floorMum;

    /**
     * 避难层数
     */
    @TableField(value = "refuge_layer")
    private String refugeLayer;

    /**
     * 避难层位置
     */
    @TableField(value = "refuge_level_location")
    private String refugeLevelLocation;

    /**
     * 安全出口数
     */
    @TableField(value = "emergency_mum")
    private String emergencyMum;

    /**
     * 疏散楼梯数
     */
    @TableField(value = "evacuation_mum")
    private Integer evacuationMum;

    /**
     * 消防电梯数
     */
    @TableField(value = "fire_elevators_mum")
    private Integer fireElevatorsMum;

    /**
     * 消防车道数
     */
    @TableField(value = "fire_lanes_mum")
    private Integer fireLanesMum;

    /**
     * 消防车道位置
     */
    @TableField(value = "fire_lane_location")
    private String fireLaneLocation;

    /**
     * 所属商业项目
     */
    @TableField(value = "commercial_projects")
    private String commercialProjects;

    /**
     * 单位所在层数
     */
    @TableField(value = "layer_numbers")
    private String layerNumbers;

    /**
     * 经营范围
     */
    @TableField(value = "scope_of_business")
    private String scopeOfBusiness;

    /**
     * 消防联网维保签约情况，1-已缴费，2-欠费，3-临期
     */
    @TableField(value = "contractual_status")
    private String contractualStatus;

    /**
     * 公司简介
     */
    @TableField(value = "company_profile")
    private String companyProfile;

    /**
     * 备注信息
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 消防安全责任人
     */
    @TableField(value = "fire_safe_person")
    private String fireSafePerson;

    /**
     * 消防安全责任人身份证号
     */
    @TableField(value = "fire_safe_person_id")
    private String fireSafePersonId;

    /**
     * 消防安全责任人电话
     */
    @TableField(value = "fire_safe_person_tel")
    private String fireSafePersonTel;

    /**
     * 消防安全管理人
     */
    @TableField(value = "fire_safe_manager")
    private String fireSafeManager;

    /**
     * 消防安全管理人身份证号
     */
    @TableField(value = "fire_safe_manager_id")
    private String fireSafeManagerId;

    /**
     * 消防安全管理人电话
     */
    @TableField(value = "fire_safe_manager_tel")
    private String fireSafeManagerTel;

    /**
     * 专兼职消防管理人
     */
    @TableField(value = "fptime_fire_manager")
    private String fptimeFireManager;

    /**
     * 专兼职消防管理人身份证号
     */
    @TableField(value = "fptime_fire_manager_id")
    private String fptimeFireManagerId;

    /**
     * 专兼职消防管理人电话
     */
    @TableField(value = "fptime_fire_manager_tel")
    private String fptimeFireManagerTel;

    /**
     * 法人
     */
    @TableField(value = "corporator")
    private String corporator;

    /**
     * 法人身份证号
     */
    @TableField(value = "corporator_id")
    private String corporatorId;

    /**
     * 法人电话
     */
    @TableField(value = "corporator_tel")
    private String corporatorTel;

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

    @TableField(value = "evaluation_level")
    private String evaluationLevel;

    /**
     * 地图区域边界
     */
    @TableField(value = "area_boundary")
    private String areaBoundary;

    /**
     * 所属救援部门负责人
     */
    @TableField(value = "rescue_department_charger")
    private String rescueDepartmentCharger;

    /**
     * 所属救援部门负责人电话
     */
    @TableField(value = "rescue_department_charger_tel")
    private String rescueDepartmentChargerTel;

    /**
     * 街道安全监管员
     */
    @TableField(value = "street_fire_supervisor")
    private String streetFireSupervisor;

    /**
     * 社区安全监管员
     */
    @TableField(value = "community_fire_supervisor")
    private String communityFireSupervisor;

    /**
     * 操作工
     */
    @TableField(value = "`operator`")
    private String operator;

    /**
     * 生产经营许可证
     */
    @TableField(value = "company_license")
    private String companyLicense;

    /**
     * 操作工证
     */
    @TableField(value = "operator_license")
    private String operatorLicense;

    /**
     * 单位图片
     */
    @TableField(value = "company_pics")
    private String companyPics;

    /**
     * 厨房面积
     */
    @TableField(value = "kitchen_area")
    private Double kitchenArea;

    /**
     * 学校类型
     */
    @TableField(value = "school_type")
    private Integer schoolType;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
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
     * 获取联网单位名称
     *
     * @return company_name - 联网单位名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置联网单位名称
     *
     * @param companyName 联网单位名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取行业主管部门
     *
     * @return industry_dept - 行业主管部门
     */
    public String getIndustryDept() {
        return industryDept;
    }

    /**
     * 设置行业主管部门
     *
     * @param industryDept 行业主管部门
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
     * 获取主管消防监督员
     *
     * @return charge_fire_supervisor - 主管消防监督员
     */
    public String getChargeFireSupervisor() {
        return chargeFireSupervisor;
    }

    /**
     * 设置主管消防监督员
     *
     * @param chargeFireSupervisor 主管消防监督员
     */
    public void setChargeFireSupervisor(String chargeFireSupervisor) {
        this.chargeFireSupervisor = chargeFireSupervisor;
    }

    /**
     * 获取协管消防监督员
     *
     * @return assist_fire_supervisor - 协管消防监督员
     */
    public String getAssistFireSupervisor() {
        return assistFireSupervisor;
    }

    /**
     * 设置协管消防监督员
     *
     * @param assistFireSupervisor 协管消防监督员
     */
    public void setAssistFireSupervisor(String assistFireSupervisor) {
        this.assistFireSupervisor = assistFireSupervisor;
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
     * 获取施工单位
     *
     * @return construction_unit - 施工单位
     */
    public String getConstructionUnit() {
        return constructionUnit;
    }

    /**
     * 设置施工单位
     *
     * @param constructionUnit 施工单位
     */
    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    /**
     * 获取检测公司
     *
     * @return check_unit - 检测公司
     */
    public String getCheckUnit() {
        return checkUnit;
    }

    /**
     * 设置检测公司
     *
     * @param checkUnit 检测公司
     */
    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
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
     * 获取地图属性，0-百度地图，1-三维模式，2-2.5D地图
     *
     * @return map_type - 地图属性，0-百度地图，1-三维模式，2-2.5D地图
     */
    public Integer getMapType() {
        return mapType;
    }

    /**
     * 设置地图属性，0-百度地图，1-三维模式，2-2.5D地图
     *
     * @param mapType 地图属性，0-百度地图，1-三维模式，2-2.5D地图
     */
    public void setMapType(Integer mapType) {
        this.mapType = mapType;
    }

    /**
     * 获取单位图标
     *
     * @return company_icon - 单位图标
     */
    public String getCompanyIcon() {
        return companyIcon;
    }

    /**
     * 设置单位图标
     *
     * @param companyIcon 单位图标
     */
    public void setCompanyIcon(String companyIcon) {
        this.companyIcon = companyIcon;
    }

    /**
     * 获取成立时间
     *
     * @return org_license_time - 成立时间
     */
    public Date getOrgLicenseTime() {
        return orgLicenseTime;
    }

    /**
     * 设置成立时间
     *
     * @param orgLicenseTime 成立时间
     */
    public void setOrgLicenseTime(Date orgLicenseTime) {
        this.orgLicenseTime = orgLicenseTime;
    }

    /**
     * 获取邮政编码
     *
     * @return postal_code - 邮政编码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalCode 邮政编码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 获取职工总人数
     *
     * @return work_num - 职工总人数
     */
    public Integer getWorkNum() {
        return workNum;
    }

    /**
     * 设置职工总人数
     *
     * @param workNum 职工总人数
     */
    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    /**
     * 获取固定资产（万）
     *
     * @return fixed_assets - 固定资产（万）
     */
    public Double getFixedAssets() {
        return fixedAssets;
    }

    /**
     * 设置固定资产（万）
     *
     * @param fixedAssets 固定资产（万）
     */
    public void setFixedAssets(Double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    /**
     * 获取经济所有制
     *
     * @return economic_ownership - 经济所有制
     */
    public String getEconomicOwnership() {
        return economicOwnership;
    }

    /**
     * 设置经济所有制
     *
     * @param economicOwnership 经济所有制
     */
    public void setEconomicOwnership(String economicOwnership) {
        this.economicOwnership = economicOwnership;
    }

    /**
     * 获取单位占地面积（平方米）
     *
     * @return unit_area - 单位占地面积（平方米）
     */
    public Double getUnitArea() {
        return unitArea;
    }

    /**
     * 设置单位占地面积（平方米）
     *
     * @param unitArea 单位占地面积（平方米）
     */
    public void setUnitArea(Double unitArea) {
        this.unitArea = unitArea;
    }

    /**
     * 获取总建筑面积（平方米）
     *
     * @return total_building_area - 总建筑面积（平方米）
     */
    public Double getTotalBuildingArea() {
        return totalBuildingArea;
    }

    /**
     * 设置总建筑面积（平方米）
     *
     * @param totalBuildingArea 总建筑面积（平方米）
     */
    public void setTotalBuildingArea(Double totalBuildingArea) {
        this.totalBuildingArea = totalBuildingArea;
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
     * 获取所属救援部门
     *
     * @return rescue_department - 所属救援部门
     */
    public String getRescueDepartment() {
        return rescueDepartment;
    }

    /**
     * 设置所属救援部门
     *
     * @param rescueDepartment 所属救援部门
     */
    public void setRescueDepartment(String rescueDepartment) {
        this.rescueDepartment = rescueDepartment;
    }

    /**
     * 获取监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-个人用户，99-其他单位
     *
     * @return monitor_type - 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-个人用户，99-其他单位
     */
    public String getMonitorType() {
        return monitorType;
    }

    /**
     * 设置监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-个人用户，99-其他单位
     *
     * @param monitorType 监管类别，1-重点单位，2-一般单位，3-小场所，4-火灾高危单位，5-住宅小区，6-个人用户，99-其他单位
     */
    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
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
     * 获取入网日期
     *
     * @return access_date - 入网日期
     */
    public Date getAccessDate() {
        return accessDate;
    }

    /**
     * 设置入网日期
     *
     * @param accessDate 入网日期
     */
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
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
     * 获取防火监督员
     *
     * @return fire_supervisor - 防火监督员
     */
    public String getFireSupervisor() {
        return fireSupervisor;
    }

    /**
     * 设置防火监督员
     *
     * @param fireSupervisor 防火监督员
     */
    public void setFireSupervisor(String fireSupervisor) {
        this.fireSupervisor = fireSupervisor;
    }

    /**
     * 获取防火监督员电话
     *
     * @return fire_supervisor_tel - 防火监督员电话
     */
    public String getFireSupervisorTel() {
        return fireSupervisorTel;
    }

    /**
     * 设置防火监督员电话
     *
     * @param fireSupervisorTel 防火监督员电话
     */
    public void setFireSupervisorTel(String fireSupervisorTel) {
        this.fireSupervisorTel = fireSupervisorTel;
    }

    /**
     * 获取营业时最大人数
     *
     * @return max_mum_people - 营业时最大人数
     */
    public String getMaxMumPeople() {
        return maxMumPeople;
    }

    /**
     * 设置营业时最大人数
     *
     * @param maxMumPeople 营业时最大人数
     */
    public void setMaxMumPeople(String maxMumPeople) {
        this.maxMumPeople = maxMumPeople;
    }

    /**
     * 获取是否有自动消防设施，0-无，1-有
     *
     * @return is_automatic_fire - 是否有自动消防设施，0-无，1-有
     */
    public String getIsAutomaticFire() {
        return isAutomaticFire;
    }

    /**
     * 设置是否有自动消防设施，0-无，1-有
     *
     * @param isAutomaticFire 是否有自动消防设施，0-无，1-有
     */
    public void setIsAutomaticFire(String isAutomaticFire) {
        this.isAutomaticFire = isAutomaticFire;
    }

    /**
     * 获取单位专职(志愿)消防员数
     *
     * @return fire_fighters_mum - 单位专职(志愿)消防员数
     */
    public String getFireFightersMum() {
        return fireFightersMum;
    }

    /**
     * 设置单位专职(志愿)消防员数
     *
     * @param fireFightersMum 单位专职(志愿)消防员数
     */
    public void setFireFightersMum(String fireFightersMum) {
        this.fireFightersMum = fireFightersMum;
    }

    /**
     * 获取避难层总面积（平方米）
     *
     * @return floor_mum - 避难层总面积（平方米）
     */
    public String getFloorMum() {
        return floorMum;
    }

    /**
     * 设置避难层总面积（平方米）
     *
     * @param floorMum 避难层总面积（平方米）
     */
    public void setFloorMum(String floorMum) {
        this.floorMum = floorMum;
    }

    /**
     * 获取避难层数
     *
     * @return refuge_layer - 避难层数
     */
    public String getRefugeLayer() {
        return refugeLayer;
    }

    /**
     * 设置避难层数
     *
     * @param refugeLayer 避难层数
     */
    public void setRefugeLayer(String refugeLayer) {
        this.refugeLayer = refugeLayer;
    }

    /**
     * 获取避难层位置
     *
     * @return refuge_level_location - 避难层位置
     */
    public String getRefugeLevelLocation() {
        return refugeLevelLocation;
    }

    /**
     * 设置避难层位置
     *
     * @param refugeLevelLocation 避难层位置
     */
    public void setRefugeLevelLocation(String refugeLevelLocation) {
        this.refugeLevelLocation = refugeLevelLocation;
    }

    /**
     * 获取安全出口数
     *
     * @return emergency_mum - 安全出口数
     */
    public String getEmergencyMum() {
        return emergencyMum;
    }

    /**
     * 设置安全出口数
     *
     * @param emergencyMum 安全出口数
     */
    public void setEmergencyMum(String emergencyMum) {
        this.emergencyMum = emergencyMum;
    }

    /**
     * 获取疏散楼梯数
     *
     * @return evacuation_mum - 疏散楼梯数
     */
    public Integer getEvacuationMum() {
        return evacuationMum;
    }

    /**
     * 设置疏散楼梯数
     *
     * @param evacuationMum 疏散楼梯数
     */
    public void setEvacuationMum(Integer evacuationMum) {
        this.evacuationMum = evacuationMum;
    }

    /**
     * 获取消防电梯数
     *
     * @return fire_elevators_mum - 消防电梯数
     */
    public Integer getFireElevatorsMum() {
        return fireElevatorsMum;
    }

    /**
     * 设置消防电梯数
     *
     * @param fireElevatorsMum 消防电梯数
     */
    public void setFireElevatorsMum(Integer fireElevatorsMum) {
        this.fireElevatorsMum = fireElevatorsMum;
    }

    /**
     * 获取消防车道数
     *
     * @return fire_lanes_mum - 消防车道数
     */
    public Integer getFireLanesMum() {
        return fireLanesMum;
    }

    /**
     * 设置消防车道数
     *
     * @param fireLanesMum 消防车道数
     */
    public void setFireLanesMum(Integer fireLanesMum) {
        this.fireLanesMum = fireLanesMum;
    }

    /**
     * 获取消防车道位置
     *
     * @return fire_lane_location - 消防车道位置
     */
    public String getFireLaneLocation() {
        return fireLaneLocation;
    }

    /**
     * 设置消防车道位置
     *
     * @param fireLaneLocation 消防车道位置
     */
    public void setFireLaneLocation(String fireLaneLocation) {
        this.fireLaneLocation = fireLaneLocation;
    }

    /**
     * 获取所属商业项目
     *
     * @return commercial_projects - 所属商业项目
     */
    public String getCommercialProjects() {
        return commercialProjects;
    }

    /**
     * 设置所属商业项目
     *
     * @param commercialProjects 所属商业项目
     */
    public void setCommercialProjects(String commercialProjects) {
        this.commercialProjects = commercialProjects;
    }

    /**
     * 获取单位所在层数
     *
     * @return layer_numbers - 单位所在层数
     */
    public String getLayerNumbers() {
        return layerNumbers;
    }

    /**
     * 设置单位所在层数
     *
     * @param layerNumbers 单位所在层数
     */
    public void setLayerNumbers(String layerNumbers) {
        this.layerNumbers = layerNumbers;
    }

    /**
     * 获取经营范围
     *
     * @return scope_of_business - 经营范围
     */
    public String getScopeOfBusiness() {
        return scopeOfBusiness;
    }

    /**
     * 设置经营范围
     *
     * @param scopeOfBusiness 经营范围
     */
    public void setScopeOfBusiness(String scopeOfBusiness) {
        this.scopeOfBusiness = scopeOfBusiness;
    }

    /**
     * 获取消防联网维保签约情况，1-已缴费，2-欠费，3-临期
     *
     * @return contractual_status - 消防联网维保签约情况，1-已缴费，2-欠费，3-临期
     */
    public String getContractualStatus() {
        return contractualStatus;
    }

    /**
     * 设置消防联网维保签约情况，1-已缴费，2-欠费，3-临期
     *
     * @param contractualStatus 消防联网维保签约情况，1-已缴费，2-欠费，3-临期
     */
    public void setContractualStatus(String contractualStatus) {
        this.contractualStatus = contractualStatus;
    }

    /**
     * 获取公司简介
     *
     * @return company_profile - 公司简介
     */
    public String getCompanyProfile() {
        return companyProfile;
    }

    /**
     * 设置公司简介
     *
     * @param companyProfile 公司简介
     */
    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
     * 获取消防安全责任人身份证号
     *
     * @return fire_safe_person_id - 消防安全责任人身份证号
     */
    public String getFireSafePersonId() {
        return fireSafePersonId;
    }

    /**
     * 设置消防安全责任人身份证号
     *
     * @param fireSafePersonId 消防安全责任人身份证号
     */
    public void setFireSafePersonId(String fireSafePersonId) {
        this.fireSafePersonId = fireSafePersonId;
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
     * 获取消防安全管理人
     *
     * @return fire_safe_manager - 消防安全管理人
     */
    public String getFireSafeManager() {
        return fireSafeManager;
    }

    /**
     * 设置消防安全管理人
     *
     * @param fireSafeManager 消防安全管理人
     */
    public void setFireSafeManager(String fireSafeManager) {
        this.fireSafeManager = fireSafeManager;
    }

    /**
     * 获取消防安全管理人身份证号
     *
     * @return fire_safe_manager_id - 消防安全管理人身份证号
     */
    public String getFireSafeManagerId() {
        return fireSafeManagerId;
    }

    /**
     * 设置消防安全管理人身份证号
     *
     * @param fireSafeManagerId 消防安全管理人身份证号
     */
    public void setFireSafeManagerId(String fireSafeManagerId) {
        this.fireSafeManagerId = fireSafeManagerId;
    }

    /**
     * 获取消防安全管理人电话
     *
     * @return fire_safe_manager_tel - 消防安全管理人电话
     */
    public String getFireSafeManagerTel() {
        return fireSafeManagerTel;
    }

    /**
     * 设置消防安全管理人电话
     *
     * @param fireSafeManagerTel 消防安全管理人电话
     */
    public void setFireSafeManagerTel(String fireSafeManagerTel) {
        this.fireSafeManagerTel = fireSafeManagerTel;
    }

    /**
     * 获取专兼职消防管理人
     *
     * @return fptime_fire_manager - 专兼职消防管理人
     */
    public String getFptimeFireManager() {
        return fptimeFireManager;
    }

    /**
     * 设置专兼职消防管理人
     *
     * @param fptimeFireManager 专兼职消防管理人
     */
    public void setFptimeFireManager(String fptimeFireManager) {
        this.fptimeFireManager = fptimeFireManager;
    }

    /**
     * 获取专兼职消防管理人身份证号
     *
     * @return fptime_fire_manager_id - 专兼职消防管理人身份证号
     */
    public String getFptimeFireManagerId() {
        return fptimeFireManagerId;
    }

    /**
     * 设置专兼职消防管理人身份证号
     *
     * @param fptimeFireManagerId 专兼职消防管理人身份证号
     */
    public void setFptimeFireManagerId(String fptimeFireManagerId) {
        this.fptimeFireManagerId = fptimeFireManagerId;
    }

    /**
     * 获取专兼职消防管理人电话
     *
     * @return fptime_fire_manager_tel - 专兼职消防管理人电话
     */
    public String getFptimeFireManagerTel() {
        return fptimeFireManagerTel;
    }

    /**
     * 设置专兼职消防管理人电话
     *
     * @param fptimeFireManagerTel 专兼职消防管理人电话
     */
    public void setFptimeFireManagerTel(String fptimeFireManagerTel) {
        this.fptimeFireManagerTel = fptimeFireManagerTel;
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
     * 获取法人身份证号
     *
     * @return corporator_id - 法人身份证号
     */
    public String getCorporatorId() {
        return corporatorId;
    }

    /**
     * 设置法人身份证号
     *
     * @param corporatorId 法人身份证号
     */
    public void setCorporatorId(String corporatorId) {
        this.corporatorId = corporatorId;
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
     * 获取地图区域边界
     *
     * @return area_boundary - 地图区域边界
     */
    public String getAreaBoundary() {
        return areaBoundary;
    }

    /**
     * 设置地图区域边界
     *
     * @param areaBoundary 地图区域边界
     */
    public void setAreaBoundary(String areaBoundary) {
        this.areaBoundary = areaBoundary;
    }

    /**
     * 获取所属救援部门负责人
     *
     * @return rescue_department_charger - 所属救援部门负责人
     */
    public String getRescueDepartmentCharger() {
        return rescueDepartmentCharger;
    }

    /**
     * 设置所属救援部门负责人
     *
     * @param rescueDepartmentCharger 所属救援部门负责人
     */
    public void setRescueDepartmentCharger(String rescueDepartmentCharger) {
        this.rescueDepartmentCharger = rescueDepartmentCharger;
    }

    /**
     * 获取所属救援部门负责人电话
     *
     * @return rescue_department_charger_tel - 所属救援部门负责人电话
     */
    public String getRescueDepartmentChargerTel() {
        return rescueDepartmentChargerTel;
    }

    /**
     * 设置所属救援部门负责人电话
     *
     * @param rescueDepartmentChargerTel 所属救援部门负责人电话
     */
    public void setRescueDepartmentChargerTel(String rescueDepartmentChargerTel) {
        this.rescueDepartmentChargerTel = rescueDepartmentChargerTel;
    }

    /**
     * 获取街道安全监管员
     *
     * @return street_fire_supervisor - 街道安全监管员
     */
    public String getStreetFireSupervisor() {
        return streetFireSupervisor;
    }

    /**
     * 设置街道安全监管员
     *
     * @param streetFireSupervisor 街道安全监管员
     */
    public void setStreetFireSupervisor(String streetFireSupervisor) {
        this.streetFireSupervisor = streetFireSupervisor;
    }

    /**
     * 获取社区安全监管员
     *
     * @return community_fire_supervisor - 社区安全监管员
     */
    public String getCommunityFireSupervisor() {
        return communityFireSupervisor;
    }

    /**
     * 设置社区安全监管员
     *
     * @param communityFireSupervisor 社区安全监管员
     */
    public void setCommunityFireSupervisor(String communityFireSupervisor) {
        this.communityFireSupervisor = communityFireSupervisor;
    }

    /**
     * 获取操作工
     *
     * @return operator - 操作工
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作工
     *
     * @param operator 操作工
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取生产经营许可证
     *
     * @return company_license - 生产经营许可证
     */
    public String getCompanyLicense() {
        return companyLicense;
    }

    /**
     * 设置生产经营许可证
     *
     * @param companyLicense 生产经营许可证
     */
    public void setCompanyLicense(String companyLicense) {
        this.companyLicense = companyLicense;
    }

    /**
     * 获取操作工证
     *
     * @return operator_license - 操作工证
     */
    public String getOperatorLicense() {
        return operatorLicense;
    }

    /**
     * 设置操作工证
     *
     * @param operatorLicense 操作工证
     */
    public void setOperatorLicense(String operatorLicense) {
        this.operatorLicense = operatorLicense;
    }

    /**
     * 获取单位图片
     *
     * @return company_pics - 单位图片
     */
    public String getCompanyPics() {
        return companyPics;
    }

    /**
     * 设置单位图片
     *
     * @param companyPics 单位图片
     */
    public void setCompanyPics(String companyPics) {
        this.companyPics = companyPics;
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