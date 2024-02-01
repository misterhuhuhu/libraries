package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
    * 商户节点表
    */
@ToString
@Data
public class SupMerchantNodeTblWithRegion {
    private String merchantNodeName;
    private String namecn;
    private String merchantNodeCode;
    private String Longitude;
    private String Latitude;
    private String detailAdress;
    private String contacts;
    private String phone;
    private String merchantType;
    private String status;
    private String code;
}