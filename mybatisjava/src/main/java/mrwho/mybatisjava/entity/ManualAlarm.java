package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

/**
    * 人工报警
    */
@TableName(value = "ct_manual_alarm")
public class ManualAlarm {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 所属区域
     */
    @TableField(value = "area_code")
    private String areaCode;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private BigDecimal latitude;

    /**
     * 报警人
     */
    @TableField(value = "alarm_person")
    private String alarmPerson;

    /**
     * 报警时间
     */
    @TableField(value = "happen_time")
    private Date happenTime;

    /**
     * 报警人电话
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 警情处理状态(0未处理1真实火警2误报3测试)
     */
    @TableField(value = "handle_status")
    private String handleStatus;

    /**
     * 处理人
     */
    @TableField(value = "handle_person")
    private String handlePerson;

    /**
     * 处理时间
     */
    @TableField(value = "handle_time")
    private Date handleTime;

    /**
     * 状态(0正常 1删除)
     */
    @TableField(value = "`status`")
    private Integer status;

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
     * 是否删除(0、未删除 1、已删除)
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建部门
     */
    @TableField(value = "create_dept")
    private String createDept;

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
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取报警人
     *
     * @return alarm_person - 报警人
     */
    public String getAlarmPerson() {
        return alarmPerson;
    }

    /**
     * 设置报警人
     *
     * @param alarmPerson 报警人
     */
    public void setAlarmPerson(String alarmPerson) {
        this.alarmPerson = alarmPerson;
    }

    /**
     * 获取报警时间
     *
     * @return happen_time - 报警时间
     */
    public Date getHappenTime() {
        return happenTime;
    }

    /**
     * 设置报警时间
     *
     * @param happenTime 报警时间
     */
    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    /**
     * 获取报警人电话
     *
     * @return tel - 报警人电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置报警人电话
     *
     * @param tel 报警人电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取警情处理状态(0未处理1真实火警2误报3测试)
     *
     * @return handle_status - 警情处理状态(0未处理1真实火警2误报3测试)
     */
    public String getHandleStatus() {
        return handleStatus;
    }

    /**
     * 设置警情处理状态(0未处理1真实火警2误报3测试)
     *
     * @param handleStatus 警情处理状态(0未处理1真实火警2误报3测试)
     */
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    /**
     * 获取处理人
     *
     * @return handle_person - 处理人
     */
    public String getHandlePerson() {
        return handlePerson;
    }

    /**
     * 设置处理人
     *
     * @param handlePerson 处理人
     */
    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

    /**
     * 获取处理时间
     *
     * @return handle_time - 处理时间
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * 设置处理时间
     *
     * @param handleTime 处理时间
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * 获取状态(0正常 1删除)
     *
     * @return status - 状态(0正常 1删除)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(0正常 1删除)
     *
     * @param status 状态(0正常 1删除)
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}