package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
        @TableField(value = "time", fill = FieldFill.INSERT)
    private String time;

    /**
     * 用户id
     */
    private Long userId;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    @Version
    private Integer version;

}