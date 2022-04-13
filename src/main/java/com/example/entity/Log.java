package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.extension.activerecord.Model;


@Data
@TableName("t_log")
public class Log extends Model<Log> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 操作内容
      */
    private String content;

    /**
      * 操作时间
      */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @TableField(value = "time", fill = FieldFill.INSERT)
    private String time;

    /**
      * 操作人
      */
    private String user;

    private String ip;

}
