package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
    * 用户表
    */
@TableName(value = "blade_user")
public class BladeUser {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private String tenantId;

    /**
     * 用户编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 用户平台
     */
    @TableField(value = "user_type")
    private Integer userType;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "`name`")
    private String name;

    @TableField(value = "real_name")
    private String realName;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "email")
    private String email;

    /**
     * 手机
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private String deptId;

    /**
     * 区域
     */
    @TableField(value = "area_code")
    private String areaCode;

    /**
     * 岗位id
     */
    @TableField(value = "post_id")
    private String postId;

    /**
     * 有效截止时间
     */
    @TableField(value = "effective_date")
    private Date effectiveDate;

    /**
     * 推送消息类型（电话、短信，多个逗号分隔）
     */
    @TableField(value = "message_push_type")
    private String messagePushType;

    /**
     * 是否接收推送消息 字典：sys_yes_no
     */
    @TableField(value = "receive_push_message")
    private String receivePushMessage;

    /**
     * 附件id
     */
    @TableField(value = "attach_id")
    private String attachId;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建部门
     */
    @TableField(value = "create_dept")
    private String createDept;

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
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 是否已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 来源 0:系统 1：注册 3:OA
     */
    @TableField(value = "resource_type")
    private String resourceType;

    /**
     * 从业时间
     */
    @TableField(value = "work_time")
    private Date workTime;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private String departmentId;

    /**
     * 微信openId
     */
    @TableField(value = "open_id")
    private String openId;

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
     * 获取租户ID
     *
     * @return tenant_id - 租户ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户ID
     *
     * @param tenantId 租户ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取用户编号
     *
     * @return code - 用户编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用户编号
     *
     * @param code 用户编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取用户平台
     *
     * @return user_type - 用户平台
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户平台
     *
     * @param userType 用户平台
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取部门id
     *
     * @return dept_id - 部门id
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id
     *
     * @param deptId 部门id
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取区域
     *
     * @return area_code - 区域
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区域
     *
     * @param areaCode 区域
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取岗位id
     *
     * @return post_id - 岗位id
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置岗位id
     *
     * @param postId 岗位id
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * 获取有效截止时间
     *
     * @return effective_date - 有效截止时间
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置有效截止时间
     *
     * @param effectiveDate 有效截止时间
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 获取推送消息类型（电话、短信，多个逗号分隔）
     *
     * @return message_push_type - 推送消息类型（电话、短信，多个逗号分隔）
     */
    public String getMessagePushType() {
        return messagePushType;
    }

    /**
     * 设置推送消息类型（电话、短信，多个逗号分隔）
     *
     * @param messagePushType 推送消息类型（电话、短信，多个逗号分隔）
     */
    public void setMessagePushType(String messagePushType) {
        this.messagePushType = messagePushType;
    }

    /**
     * 获取是否接收推送消息 字典：sys_yes_no
     *
     * @return receive_push_message - 是否接收推送消息 字典：sys_yes_no
     */
    public String getReceivePushMessage() {
        return receivePushMessage;
    }

    /**
     * 设置是否接收推送消息 字典：sys_yes_no
     *
     * @param receivePushMessage 是否接收推送消息 字典：sys_yes_no
     */
    public void setReceivePushMessage(String receivePushMessage) {
        this.receivePushMessage = receivePushMessage;
    }

    /**
     * 获取附件id
     *
     * @return attach_id - 附件id
     */
    public String getAttachId() {
        return attachId;
    }

    /**
     * 设置附件id
     *
     * @param attachId 附件id
     */
    public void setAttachId(String attachId) {
        this.attachId = attachId;
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
     * 获取是否已删除
     *
     * @return is_deleted - 是否已删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已删除
     *
     * @param isDeleted 是否已删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取来源 0:系统 1：注册 3:OA
     *
     * @return resource_type - 来源 0:系统 1：注册 3:OA
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置来源 0:系统 1：注册 3:OA
     *
     * @param resourceType 来源 0:系统 1：注册 3:OA
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取从业时间
     *
     * @return work_time - 从业时间
     */
    public Date getWorkTime() {
        return workTime;
    }

    /**
     * 设置从业时间
     *
     * @param workTime 从业时间
     */
    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    /**
     * 获取部门id
     *
     * @return department_id - 部门id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门id
     *
     * @param departmentId 部门id
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取微信openId
     *
     * @return open_id - 微信openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信openId
     *
     * @param openId 微信openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}