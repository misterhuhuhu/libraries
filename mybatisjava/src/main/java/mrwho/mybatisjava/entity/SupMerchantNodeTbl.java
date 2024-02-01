package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

import java.util.Date;

/**
    * 商户节点表
    */
@ToString
@TableName(value = "sup_merchant_node_tbl")
public class SupMerchantNodeTbl {
    /**
     * 节点id
     */
    @TableId(value = "MERCHANT_NODE_ID", type = IdType.INPUT)
    private Long merchantNodeId;

    /**
     * 归属租户ID
     */
    @TableField(value = "TENANT_ID")
    private Long tenantId;

    /**
     * 节点名称
     */
    @TableField(value = "MERCHANT_NODE_NAME")
    private String merchantNodeName;

    /**
     * 父节点ID
     */
    @TableField(value = "PARENT_ID")
    private Long parentId;

    /**
     * 完整节点路径
     */
    @TableField(value = "`PATH`")
    private String path;

    /**
     * 节点类型(1:管理节点 2：商户节点)
     */
    @TableField(value = "NODE_TYPE")
    private Byte nodeType;

    /**
     * 省的区域ID
     */
    @TableField(value = "PROVINCE_REGION_ID")
    private Long provinceRegionId;

    /**
     * 市的区域ID
     */
    @TableField(value = "CITY_REGION_ID")
    private Long cityRegionId;

    /**
     * 区/县的区域ID
     */
    @TableField(value = "DISTRICT_REGION_ID")
    private Long districtRegionId;

    /**
     * 街道的区域ID
     */
    @TableField(value = "STREET_REGION_ID")
    private Long streetRegionId;

    /**
     * 地址信息,商户必填（省/市/区）
     */
    @TableField(value = "REGION_INFO")
    private String regionInfo;

    /**
     * 地址信息Id
     */
    @TableField(value = "REGION_INFO_ID")
    private String regionInfoId;

    /**
     * 详细地址,商户必填
     */
    @TableField(value = "DETAIL_ADRESS")
    private String detailAdress;

    /**
     * 经度
     */
    @TableField(value = "LONGITUDE")
    private String longitude;

    /**
     * 纬度
     */
    @TableField(value = "LATITUDE")
    private String latitude;

    /**
     * 监管状态,商户必填(0:普通 1:重点监管)
     */
    @TableField(value = "SUP_STATUS")
    private Byte supStatus;

    /**
     * 联系人
     */
    @TableField(value = "CONTACTS")
    private String contacts;

    /**
     * 电话
     */
    @TableField(value = "PHONE")
    private String phone;

    /**
     * 综合评分
     */
    @TableField(value = "OVERALL_SCORE")
    private Double overallScore;

    /**
     * 访问量
     */
    @TableField(value = "VISIT_CNT")
    private Long visitCnt;

    /**
     * 创建时间
     */
    @TableField(value = "CREATETIME")
    private Date createtime;

    /**
     * 修改时间
     */
    @TableField(value = "UPDATETIME")
    private Date updatetime;

    /**
     * 删除标志位
     */
    @TableField(value = "`STATUS`")
    private String status;

    /**
     * 完整节点路径名称
     */
    @TableField(value = "path_name")
    private String pathName;

    /**
     * 二维码生成文本
     */
    @TableField(value = "QR_CODE")
    private String qrCode;

    @TableField(value = "ANNUAL_GRADE")
    private String annualGrade;

    @TableField(value = "SAFETY_GRADE")
    private String safetyGrade;

    @TableField(value = "SAVE_STATES")
    private String saveStates;

    @TableField(value = "CURR_SAFTY_GRADE")
    private String currSaftyGrade;

    /**
     * 商家类型1:幼儿园2:小学3:中学4:职校5:省属中职6:高校7:其他
     */
    @TableField(value = "Merchant_TYPE")
    private Integer merchantType;

    /**
     * 商家子类型
     */
    @TableField(value = "Merchant_SUB_TYPE")
    private Integer merchantSubType;

    /**
     * 许可证号
     */
    @TableField(value = "LICENSE")
    private String license;

    /**
     * 是,面向公众；否，不面向公众,餐饮类：默认公开，其余类型默认不公开
     */
    @TableField(value = "OPEN_STATUS")
    private Byte openStatus;

    /**
     * H5二维码生成文本
     */
    @TableField(value = "H5_QR_CODE")
    private String h5QrCode;

    /**
     * 量化等级 1优秀、2良好、3一般、4未评定
     */
    @TableField(value = "quantification_grade")
    private Integer quantificationGrade;

    /**
     * 风险等级
     */
    @TableField(value = "risk_level")
    private Integer riskLevel;

    /**
     * 静态评分
     */
    @TableField(value = "static_score")
    private String staticScore;

    /**
     * 营业状态
     */
    @TableField(value = "operating_state")
    private Integer operatingState;

    /**
     * 监管所id  sup_regulation_info_tbl
     */
    @TableField(value = "regulation_id")
    private Long regulationId;

    @TableField(value = "sort")
    private Integer sort;

    /**
     * 加密手机号
     */
    @TableField(value = "phoneEncrypt")
    private String phoneencrypt;

    /**
     * 摄像头设备数量
     */
    @TableField(value = "device_count")
    private Long deviceCount;

    /**
     * 商户编码
     */
    @TableField(value = "MERCHANT_NODE_CODE")
    private String merchantNodeCode;

    /**
     * 食堂数参考值（默认值1）
     */
    @TableField(value = "canteen_cnt_ck")
    private Integer canteenCntCk;

    /**
     * 行政类型
     */
    @TableField(value = "admin_type")
    private String adminType;

    /**
     * 行政编号
     */
    @TableField(value = "admin_code")
    private String adminCode;

    /**
     * 是否市直（1是，0否）
     */
    @TableField(value = "is_city_manage")
    private Byte isCityManage;

    /**
     * 获取节点id
     *
     * @return MERCHANT_NODE_ID - 节点id
     */
    public Long getMerchantNodeId() {
        return merchantNodeId;
    }

    /**
     * 设置节点id
     *
     * @param merchantNodeId 节点id
     */
    public void setMerchantNodeId(Long merchantNodeId) {
        this.merchantNodeId = merchantNodeId;
    }

    /**
     * 获取归属租户ID
     *
     * @return TENANT_ID - 归属租户ID
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * 设置归属租户ID
     *
     * @param tenantId 归属租户ID
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取节点名称
     *
     * @return MERCHANT_NODE_NAME - 节点名称
     */
    public String getMerchantNodeName() {
        return merchantNodeName;
    }

    /**
     * 设置节点名称
     *
     * @param merchantNodeName 节点名称
     */
    public void setMerchantNodeName(String merchantNodeName) {
        this.merchantNodeName = merchantNodeName;
    }

    /**
     * 获取父节点ID
     *
     * @return PARENT_ID - 父节点ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父节点ID
     *
     * @param parentId 父节点ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取完整节点路径
     *
     * @return PATH - 完整节点路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置完整节点路径
     *
     * @param path 完整节点路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取节点类型(1:管理节点 2：商户节点)
     *
     * @return NODE_TYPE - 节点类型(1:管理节点 2：商户节点)
     */
    public Byte getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型(1:管理节点 2：商户节点)
     *
     * @param nodeType 节点类型(1:管理节点 2：商户节点)
     */
    public void setNodeType(Byte nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取省的区域ID
     *
     * @return PROVINCE_REGION_ID - 省的区域ID
     */
    public Long getProvinceRegionId() {
        return provinceRegionId;
    }

    /**
     * 设置省的区域ID
     *
     * @param provinceRegionId 省的区域ID
     */
    public void setProvinceRegionId(Long provinceRegionId) {
        this.provinceRegionId = provinceRegionId;
    }

    /**
     * 获取市的区域ID
     *
     * @return CITY_REGION_ID - 市的区域ID
     */
    public Long getCityRegionId() {
        return cityRegionId;
    }

    /**
     * 设置市的区域ID
     *
     * @param cityRegionId 市的区域ID
     */
    public void setCityRegionId(Long cityRegionId) {
        this.cityRegionId = cityRegionId;
    }

    /**
     * 获取区/县的区域ID
     *
     * @return DISTRICT_REGION_ID - 区/县的区域ID
     */
    public Long getDistrictRegionId() {
        return districtRegionId;
    }

    /**
     * 设置区/县的区域ID
     *
     * @param districtRegionId 区/县的区域ID
     */
    public void setDistrictRegionId(Long districtRegionId) {
        this.districtRegionId = districtRegionId;
    }

    /**
     * 获取街道的区域ID
     *
     * @return STREET_REGION_ID - 街道的区域ID
     */
    public Long getStreetRegionId() {
        return streetRegionId;
    }

    /**
     * 设置街道的区域ID
     *
     * @param streetRegionId 街道的区域ID
     */
    public void setStreetRegionId(Long streetRegionId) {
        this.streetRegionId = streetRegionId;
    }

    /**
     * 获取地址信息,商户必填（省/市/区）
     *
     * @return REGION_INFO - 地址信息,商户必填（省/市/区）
     */
    public String getRegionInfo() {
        return regionInfo;
    }

    /**
     * 设置地址信息,商户必填（省/市/区）
     *
     * @param regionInfo 地址信息,商户必填（省/市/区）
     */
    public void setRegionInfo(String regionInfo) {
        this.regionInfo = regionInfo;
    }

    /**
     * 获取地址信息Id
     *
     * @return REGION_INFO_ID - 地址信息Id
     */
    public String getRegionInfoId() {
        return regionInfoId;
    }

    /**
     * 设置地址信息Id
     *
     * @param regionInfoId 地址信息Id
     */
    public void setRegionInfoId(String regionInfoId) {
        this.regionInfoId = regionInfoId;
    }

    /**
     * 获取详细地址,商户必填
     *
     * @return DETAIL_ADRESS - 详细地址,商户必填
     */
    public String getDetailAdress() {
        return detailAdress;
    }

    /**
     * 设置详细地址,商户必填
     *
     * @param detailAdress 详细地址,商户必填
     */
    public void setDetailAdress(String detailAdress) {
        this.detailAdress = detailAdress;
    }

    /**
     * 获取经度
     *
     * @return LONGITUDE - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return LATITUDE - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取监管状态,商户必填(0:普通 1:重点监管)
     *
     * @return SUP_STATUS - 监管状态,商户必填(0:普通 1:重点监管)
     */
    public Byte getSupStatus() {
        return supStatus;
    }

    /**
     * 设置监管状态,商户必填(0:普通 1:重点监管)
     *
     * @param supStatus 监管状态,商户必填(0:普通 1:重点监管)
     */
    public void setSupStatus(Byte supStatus) {
        this.supStatus = supStatus;
    }

    /**
     * 获取联系人
     *
     * @return CONTACTS - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取电话
     *
     * @return PHONE - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取综合评分
     *
     * @return OVERALL_SCORE - 综合评分
     */
    public Double getOverallScore() {
        return overallScore;
    }

    /**
     * 设置综合评分
     *
     * @param overallScore 综合评分
     */
    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    /**
     * 获取访问量
     *
     * @return VISIT_CNT - 访问量
     */
    public Long getVisitCnt() {
        return visitCnt;
    }

    /**
     * 设置访问量
     *
     * @param visitCnt 访问量
     */
    public void setVisitCnt(Long visitCnt) {
        this.visitCnt = visitCnt;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取修改时间
     *
     * @return UPDATETIME - 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取删除标志位
     *
     * @return STATUS - 删除标志位
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置删除标志位
     *
     * @param status 删除标志位
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取完整节点路径名称
     *
     * @return path_name - 完整节点路径名称
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * 设置完整节点路径名称
     *
     * @param pathName 完整节点路径名称
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * 获取二维码生成文本
     *
     * @return QR_CODE - 二维码生成文本
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置二维码生成文本
     *
     * @param qrCode 二维码生成文本
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * @return ANNUAL_GRADE
     */
    public String getAnnualGrade() {
        return annualGrade;
    }

    /**
     * @param annualGrade
     */
    public void setAnnualGrade(String annualGrade) {
        this.annualGrade = annualGrade;
    }

    /**
     * @return SAFETY_GRADE
     */
    public String getSafetyGrade() {
        return safetyGrade;
    }

    /**
     * @param safetyGrade
     */
    public void setSafetyGrade(String safetyGrade) {
        this.safetyGrade = safetyGrade;
    }

    /**
     * @return SAVE_STATES
     */
    public String getSaveStates() {
        return saveStates;
    }

    /**
     * @param saveStates
     */
    public void setSaveStates(String saveStates) {
        this.saveStates = saveStates;
    }

    /**
     * @return CURR_SAFTY_GRADE
     */
    public String getCurrSaftyGrade() {
        return currSaftyGrade;
    }

    /**
     * @param currSaftyGrade
     */
    public void setCurrSaftyGrade(String currSaftyGrade) {
        this.currSaftyGrade = currSaftyGrade;
    }

    /**
     * 获取商家类型1:幼儿园2:小学3:中学4:职校5:省属中职6:高校7:其他
     *
     * @return Merchant_TYPE - 商家类型1:幼儿园2:小学3:中学4:职校5:省属中职6:高校7:其他
     */
    public Integer getMerchantType() {
        return merchantType;
    }

    /**
     * 设置商家类型1:幼儿园2:小学3:中学4:职校5:省属中职6:高校7:其他
     *
     * @param merchantType 商家类型1:幼儿园2:小学3:中学4:职校5:省属中职6:高校7:其他
     */
    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * 获取商家子类型
     *
     * @return Merchant_SUB_TYPE - 商家子类型
     */
    public Integer getMerchantSubType() {
        return merchantSubType;
    }

    /**
     * 设置商家子类型
     *
     * @param merchantSubType 商家子类型
     */
    public void setMerchantSubType(Integer merchantSubType) {
        this.merchantSubType = merchantSubType;
    }

    /**
     * 获取许可证号
     *
     * @return LICENSE - 许可证号
     */
    public String getLicense() {
        return license;
    }

    /**
     * 设置许可证号
     *
     * @param license 许可证号
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * 获取是,面向公众；否，不面向公众,餐饮类：默认公开，其余类型默认不公开
     *
     * @return OPEN_STATUS - 是,面向公众；否，不面向公众,餐饮类：默认公开，其余类型默认不公开
     */
    public Byte getOpenStatus() {
        return openStatus;
    }

    /**
     * 设置是,面向公众；否，不面向公众,餐饮类：默认公开，其余类型默认不公开
     *
     * @param openStatus 是,面向公众；否，不面向公众,餐饮类：默认公开，其余类型默认不公开
     */
    public void setOpenStatus(Byte openStatus) {
        this.openStatus = openStatus;
    }

    /**
     * 获取H5二维码生成文本
     *
     * @return H5_QR_CODE - H5二维码生成文本
     */
    public String getH5QrCode() {
        return h5QrCode;
    }

    /**
     * 设置H5二维码生成文本
     *
     * @param h5QrCode H5二维码生成文本
     */
    public void setH5QrCode(String h5QrCode) {
        this.h5QrCode = h5QrCode;
    }

    /**
     * 获取量化等级 1优秀、2良好、3一般、4未评定
     *
     * @return quantification_grade - 量化等级 1优秀、2良好、3一般、4未评定
     */
    public Integer getQuantificationGrade() {
        return quantificationGrade;
    }

    /**
     * 设置量化等级 1优秀、2良好、3一般、4未评定
     *
     * @param quantificationGrade 量化等级 1优秀、2良好、3一般、4未评定
     */
    public void setQuantificationGrade(Integer quantificationGrade) {
        this.quantificationGrade = quantificationGrade;
    }

    /**
     * 获取风险等级
     *
     * @return risk_level - 风险等级
     */
    public Integer getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置风险等级
     *
     * @param riskLevel 风险等级
     */
    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * 获取静态评分
     *
     * @return static_score - 静态评分
     */
    public String getStaticScore() {
        return staticScore;
    }

    /**
     * 设置静态评分
     *
     * @param staticScore 静态评分
     */
    public void setStaticScore(String staticScore) {
        this.staticScore = staticScore;
    }

    /**
     * 获取营业状态
     *
     * @return operating_state - 营业状态
     */
    public Integer getOperatingState() {
        return operatingState;
    }

    /**
     * 设置营业状态
     *
     * @param operatingState 营业状态
     */
    public void setOperatingState(Integer operatingState) {
        this.operatingState = operatingState;
    }

    /**
     * 获取监管所id  sup_regulation_info_tbl
     *
     * @return regulation_id - 监管所id  sup_regulation_info_tbl
     */
    public Long getRegulationId() {
        return regulationId;
    }

    /**
     * 设置监管所id  sup_regulation_info_tbl
     *
     * @param regulationId 监管所id  sup_regulation_info_tbl
     */
    public void setRegulationId(Long regulationId) {
        this.regulationId = regulationId;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取加密手机号
     *
     * @return phoneEncrypt - 加密手机号
     */
    public String getPhoneencrypt() {
        return phoneencrypt;
    }

    /**
     * 设置加密手机号
     *
     * @param phoneencrypt 加密手机号
     */
    public void setPhoneencrypt(String phoneencrypt) {
        this.phoneencrypt = phoneencrypt;
    }

    /**
     * 获取摄像头设备数量
     *
     * @return device_count - 摄像头设备数量
     */
    public Long getDeviceCount() {
        return deviceCount;
    }

    /**
     * 设置摄像头设备数量
     *
     * @param deviceCount 摄像头设备数量
     */
    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    /**
     * 获取商户编码
     *
     * @return MERCHANT_NODE_CODE - 商户编码
     */
    public String getMerchantNodeCode() {
        return merchantNodeCode;
    }

    /**
     * 设置商户编码
     *
     * @param merchantNodeCode 商户编码
     */
    public void setMerchantNodeCode(String merchantNodeCode) {
        this.merchantNodeCode = merchantNodeCode;
    }

    /**
     * 获取食堂数参考值（默认值1）
     *
     * @return canteen_cnt_ck - 食堂数参考值（默认值1）
     */
    public Integer getCanteenCntCk() {
        return canteenCntCk;
    }

    /**
     * 设置食堂数参考值（默认值1）
     *
     * @param canteenCntCk 食堂数参考值（默认值1）
     */
    public void setCanteenCntCk(Integer canteenCntCk) {
        this.canteenCntCk = canteenCntCk;
    }

    /**
     * 获取行政类型
     *
     * @return admin_type - 行政类型
     */
    public String getAdminType() {
        return adminType;
    }

    /**
     * 设置行政类型
     *
     * @param adminType 行政类型
     */
    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    /**
     * 获取行政编号
     *
     * @return admin_code - 行政编号
     */
    public String getAdminCode() {
        return adminCode;
    }

    /**
     * 设置行政编号
     *
     * @param adminCode 行政编号
     */
    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    /**
     * 获取是否市直（1是，0否）
     *
     * @return is_city_manage - 是否市直（1是，0否）
     */
    public Byte getIsCityManage() {
        return isCityManage;
    }

    /**
     * 设置是否市直（1是，0否）
     *
     * @param isCityManage 是否市直（1是，0否）
     */
    public void setIsCityManage(Byte isCityManage) {
        this.isCityManage = isCityManage;
    }
}