package mrwho.mybatisjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
    * 消防志愿者信息
    */
@TableName(value = "ct_fire_cabinet_staff_profile")
public class FireCabinetStaffProfile {
    /**
     * user表id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 志愿者职业
     */
    @TableField(value = "career")
    private String career;

    /**
     * 志愿者参加过什么培训
     */
    @TableField(value = "train")
    private String train;

    /**
     * 参与过几次救火
     */
    @TableField(value = "fire_fight_times")
    private Integer fireFightTimes;

    /**
     * 获取user表id
     *
     * @return id - user表id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置user表id
     *
     * @param id user表id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取志愿者职业
     *
     * @return career - 志愿者职业
     */
    public String getCareer() {
        return career;
    }

    /**
     * 设置志愿者职业
     *
     * @param career 志愿者职业
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * 获取志愿者参加过什么培训
     *
     * @return train - 志愿者参加过什么培训
     */
    public String getTrain() {
        return train;
    }

    /**
     * 设置志愿者参加过什么培训
     *
     * @param train 志愿者参加过什么培训
     */
    public void setTrain(String train) {
        this.train = train;
    }

    /**
     * 获取参与过几次救火
     *
     * @return fire_fight_times - 参与过几次救火
     */
    public Integer getFireFightTimes() {
        return fireFightTimes;
    }

    /**
     * 设置参与过几次救火
     *
     * @param fireFightTimes 参与过几次救火
     */
    public void setFireFightTimes(Integer fireFightTimes) {
        this.fireFightTimes = fireFightTimes;
    }
}