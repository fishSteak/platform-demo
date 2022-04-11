package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@TableName("t_registration")
public class Registration extends Model<Registration> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 响应内容
     */
    private String context;

    /**
     * 状态0不通过 1通过
     */
    private Boolean state;

    /**
     * 申请时间(用户申请参加活动的时间)
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date time;

    /**
     * 用户id
     */
    private Long userId;

}