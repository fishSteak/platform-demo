package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

@Data
@TableName("t_activity")
public class Activity extends Model<Activity> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 内容 
      */
    private String content;

    /**
      * 结束时间 
      */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date endtime;

    /**
      * 图片 
      */
    private String img;

    /**
      * 活动名 
      */
    private String name;

    /**
      * 人数总量 
      */
    private Integer number;

    /**
     * 人数剩余
     */
    private Integer surplus;

    /**
      * 开始时间 
      */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date starttime;

    /**
      * 状态 0关闭 1开启 
      */
    private Boolean state;

    /**
      * 举办人 
      */
    private String username;

    /**
      * 乐观锁 
      */
    @ApiModelProperty("乐观锁")
    @TableField("version")
    @Version
    private Integer version;

}