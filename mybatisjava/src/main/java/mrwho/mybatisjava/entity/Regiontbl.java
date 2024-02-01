package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

/**
    * 区域表
    */
@ToString
@TableName(value = "regiontbl")
public class Regiontbl {
    @TableId(value = "regionid", type = IdType.INPUT)
    private Long regionid;

    /**
     * 中文名
     */
    @TableField(value = "namecn")
    private String namecn;

    /**
     * 英文名
     */
    @TableField(value = "namepy")
    private String namepy;

    /**
     * 别名
     */
    @TableField(value = "abbrcn")
    private String abbrcn;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    @TableField(value = "officername")
    private String officername;

    @TableField(value = "officerphone")
    private String officerphone;

    /**
     * X坐标
     */
    @TableField(value = "xposition")
    private Long xposition;

    /**
     * Y坐标
     */
    @TableField(value = "yposition")
    private Long yposition;

    /**
     * 定位X
     */
    @TableField(value = "offsetx")
    private Double offsetx;

    /**
     * 定位Y
     */
    @TableField(value = "offsety")
    private Double offsety;

    @TableField(value = "`scale`")
    private Double scale;

    @TableField(value = "version")
    private Double version;

    @TableField(value = "labelposition")
    private Integer labelposition;

    @TableField(value = "background")
    private String background;

    /**
     * 区域类型：0省级 1地市 2区县 3街道
     */
    @TableField(value = "regiontype")
    private Integer regiontype;

    /**
     * 父级区域ID
     */
    @TableField(value = "parentregionid")
    private Long parentregionid;

    /**
     * 区域编码
     */
    @TableField(value = "regioncode")
    private String regioncode;

    @TableField(value = "serviceorder")
    private Integer serviceorder;

    @TableField(value = "comments")
    private String comments;

    @TableField(value = "ticketprefix")
    private String ticketprefix;

    @TableField(value = "adminid")
    private Long adminid;

    @TableField(value = "`alias`")
    private String alias;

    @TableField(value = "regionno")
    private String regionno;

    @TableField(value = "areano")
    private String areano;

    /**
     * 别名
     */
    @TableField(value = "namemoniker")
    private String namemoniker;

    /**
     * 编码别名
     */
    @TableField(value = "codemoniker")
    private String codemoniker;

    @TableField(value = "old_id")
    private Long oldId;

    @TableField(value = "old_parentid")
    private Long oldParentid;

    @TableField(value = "migsource")
    private String migsource;

    @TableField(value = "system_title")
    private Integer systemTitle;

    /**
     * 区域缩写
     */
    @TableField(value = "net_title")
    private String netTitle;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    @TableField(value = "sourceregionid")
    private Long sourceregionid;

    @TableField(value = "sourceparentid")
    private Long sourceparentid;

    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 1-省公司   2-本地网  3-区县
     */
    @TableField(value = "region_level")
    private Integer regionLevel;

    /**
     * 祖先区域ID：用于生成树
     */
    @TableField(value = "full_region_id")
    private String fullRegionId;

    @TableField(value = "areacode")
    private String areacode;

    @TableField(value = "is_real_region")
    private Integer isRealRegion;

    /**
     * 是否有子节点 1：有 2：无
     */
    @TableField(value = "is_child_node")
    private Integer isChildNode;

    /**
     * @return regionid
     */
    public Long getRegionid() {
        return regionid;
    }

    /**
     * @param regionid
     */
    public void setRegionid(Long regionid) {
        this.regionid = regionid;
    }

    /**
     * 获取中文名
     *
     * @return namecn - 中文名
     */
    public String getNamecn() {
        return namecn;
    }

    /**
     * 设置中文名
     *
     * @param namecn 中文名
     */
    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }

    /**
     * 获取英文名
     *
     * @return namepy - 英文名
     */
    public String getNamepy() {
        return namepy;
    }

    /**
     * 设置英文名
     *
     * @param namepy 英文名
     */
    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    /**
     * 获取别名
     *
     * @return abbrcn - 别名
     */
    public String getAbbrcn() {
        return abbrcn;
    }

    /**
     * 设置别名
     *
     * @param abbrcn 别名
     */
    public void setAbbrcn(String abbrcn) {
        this.abbrcn = abbrcn;
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
     * @return officername
     */
    public String getOfficername() {
        return officername;
    }

    /**
     * @param officername
     */
    public void setOfficername(String officername) {
        this.officername = officername;
    }

    /**
     * @return officerphone
     */
    public String getOfficerphone() {
        return officerphone;
    }

    /**
     * @param officerphone
     */
    public void setOfficerphone(String officerphone) {
        this.officerphone = officerphone;
    }

    /**
     * 获取X坐标
     *
     * @return xposition - X坐标
     */
    public Long getXposition() {
        return xposition;
    }

    /**
     * 设置X坐标
     *
     * @param xposition X坐标
     */
    public void setXposition(Long xposition) {
        this.xposition = xposition;
    }

    /**
     * 获取Y坐标
     *
     * @return yposition - Y坐标
     */
    public Long getYposition() {
        return yposition;
    }

    /**
     * 设置Y坐标
     *
     * @param yposition Y坐标
     */
    public void setYposition(Long yposition) {
        this.yposition = yposition;
    }

    /**
     * 获取定位X
     *
     * @return offsetx - 定位X
     */
    public Double getOffsetx() {
        return offsetx;
    }

    /**
     * 设置定位X
     *
     * @param offsetx 定位X
     */
    public void setOffsetx(Double offsetx) {
        this.offsetx = offsetx;
    }

    /**
     * 获取定位Y
     *
     * @return offsety - 定位Y
     */
    public Double getOffsety() {
        return offsety;
    }

    /**
     * 设置定位Y
     *
     * @param offsety 定位Y
     */
    public void setOffsety(Double offsety) {
        this.offsety = offsety;
    }

    /**
     * @return scale
     */
    public Double getScale() {
        return scale;
    }

    /**
     * @param scale
     */
    public void setScale(Double scale) {
        this.scale = scale;
    }

    /**
     * @return version
     */
    public Double getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Double version) {
        this.version = version;
    }

    /**
     * @return labelposition
     */
    public Integer getLabelposition() {
        return labelposition;
    }

    /**
     * @param labelposition
     */
    public void setLabelposition(Integer labelposition) {
        this.labelposition = labelposition;
    }

    /**
     * @return background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * 获取区域类型：0省级 1地市 2区县 3街道
     *
     * @return regiontype - 区域类型：0省级 1地市 2区县 3街道
     */
    public Integer getRegiontype() {
        return regiontype;
    }

    /**
     * 设置区域类型：0省级 1地市 2区县 3街道
     *
     * @param regiontype 区域类型：0省级 1地市 2区县 3街道
     */
    public void setRegiontype(Integer regiontype) {
        this.regiontype = regiontype;
    }

    /**
     * 获取父级区域ID
     *
     * @return parentregionid - 父级区域ID
     */
    public Long getParentregionid() {
        return parentregionid;
    }

    /**
     * 设置父级区域ID
     *
     * @param parentregionid 父级区域ID
     */
    public void setParentregionid(Long parentregionid) {
        this.parentregionid = parentregionid;
    }

    /**
     * 获取区域编码
     *
     * @return regioncode - 区域编码
     */
    public String getRegioncode() {
        return regioncode;
    }

    /**
     * 设置区域编码
     *
     * @param regioncode 区域编码
     */
    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    /**
     * @return serviceorder
     */
    public Integer getServiceorder() {
        return serviceorder;
    }

    /**
     * @param serviceorder
     */
    public void setServiceorder(Integer serviceorder) {
        this.serviceorder = serviceorder;
    }

    /**
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return ticketprefix
     */
    public String getTicketprefix() {
        return ticketprefix;
    }

    /**
     * @param ticketprefix
     */
    public void setTicketprefix(String ticketprefix) {
        this.ticketprefix = ticketprefix;
    }

    /**
     * @return adminid
     */
    public Long getAdminid() {
        return adminid;
    }

    /**
     * @param adminid
     */
    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    /**
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return regionno
     */
    public String getRegionno() {
        return regionno;
    }

    /**
     * @param regionno
     */
    public void setRegionno(String regionno) {
        this.regionno = regionno;
    }

    /**
     * @return areano
     */
    public String getAreano() {
        return areano;
    }

    /**
     * @param areano
     */
    public void setAreano(String areano) {
        this.areano = areano;
    }

    /**
     * 获取别名
     *
     * @return namemoniker - 别名
     */
    public String getNamemoniker() {
        return namemoniker;
    }

    /**
     * 设置别名
     *
     * @param namemoniker 别名
     */
    public void setNamemoniker(String namemoniker) {
        this.namemoniker = namemoniker;
    }

    /**
     * 获取编码别名
     *
     * @return codemoniker - 编码别名
     */
    public String getCodemoniker() {
        return codemoniker;
    }

    /**
     * 设置编码别名
     *
     * @param codemoniker 编码别名
     */
    public void setCodemoniker(String codemoniker) {
        this.codemoniker = codemoniker;
    }

    /**
     * @return old_id
     */
    public Long getOldId() {
        return oldId;
    }

    /**
     * @param oldId
     */
    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    /**
     * @return old_parentid
     */
    public Long getOldParentid() {
        return oldParentid;
    }

    /**
     * @param oldParentid
     */
    public void setOldParentid(Long oldParentid) {
        this.oldParentid = oldParentid;
    }

    /**
     * @return migsource
     */
    public String getMigsource() {
        return migsource;
    }

    /**
     * @param migsource
     */
    public void setMigsource(String migsource) {
        this.migsource = migsource;
    }

    /**
     * @return system_title
     */
    public Integer getSystemTitle() {
        return systemTitle;
    }

    /**
     * @param systemTitle
     */
    public void setSystemTitle(Integer systemTitle) {
        this.systemTitle = systemTitle;
    }

    /**
     * 获取区域缩写
     *
     * @return net_title - 区域缩写
     */
    public String getNetTitle() {
        return netTitle;
    }

    /**
     * 设置区域缩写
     *
     * @param netTitle 区域缩写
     */
    public void setNetTitle(String netTitle) {
        this.netTitle = netTitle;
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
     * @return sourceregionid
     */
    public Long getSourceregionid() {
        return sourceregionid;
    }

    /**
     * @param sourceregionid
     */
    public void setSourceregionid(Long sourceregionid) {
        this.sourceregionid = sourceregionid;
    }

    /**
     * @return sourceparentid
     */
    public Long getSourceparentid() {
        return sourceparentid;
    }

    /**
     * @param sourceparentid
     */
    public void setSourceparentid(Long sourceparentid) {
        this.sourceparentid = sourceparentid;
    }

    /**
     * @return org_id
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取1-省公司   2-本地网  3-区县
     *
     * @return region_level - 1-省公司   2-本地网  3-区县
     */
    public Integer getRegionLevel() {
        return regionLevel;
    }

    /**
     * 设置1-省公司   2-本地网  3-区县
     *
     * @param regionLevel 1-省公司   2-本地网  3-区县
     */
    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * 获取祖先区域ID：用于生成树
     *
     * @return full_region_id - 祖先区域ID：用于生成树
     */
    public String getFullRegionId() {
        return fullRegionId;
    }

    /**
     * 设置祖先区域ID：用于生成树
     *
     * @param fullRegionId 祖先区域ID：用于生成树
     */
    public void setFullRegionId(String fullRegionId) {
        this.fullRegionId = fullRegionId;
    }

    /**
     * @return areacode
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * @param areacode
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * @return is_real_region
     */
    public Integer getIsRealRegion() {
        return isRealRegion;
    }

    /**
     * @param isRealRegion
     */
    public void setIsRealRegion(Integer isRealRegion) {
        this.isRealRegion = isRealRegion;
    }

    /**
     * 获取是否有子节点 1：有 2：无
     *
     * @return is_child_node - 是否有子节点 1：有 2：无
     */
    public Integer getIsChildNode() {
        return isChildNode;
    }

    /**
     * 设置是否有子节点 1：有 2：无
     *
     * @param isChildNode 是否有子节点 1：有 2：无
     */
    public void setIsChildNode(Integer isChildNode) {
        this.isChildNode = isChildNode;
    }
}