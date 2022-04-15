package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.entity.Activity;
import com.example.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationDTO {
    /**
     * 主键 流水号
     */
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 通过状态
     */
    private Boolean state;

    /**
     * 申请时间
     */
    private String time;

    /**
     * 响应内容
     */
    private String context;
    //    List<User> users;
    User user;
}
