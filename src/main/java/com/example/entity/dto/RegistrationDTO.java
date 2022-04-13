package com.example.entity.dto;

import com.example.entity.Activity;
import com.example.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationDTO {
    //根据一个活动ID查询出来很多用户
    private Long id;
    private List<User> userList;
    private Activity activity;

}
