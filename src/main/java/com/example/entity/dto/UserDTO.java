package com.example.entity.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String avatar;
    private String address;
    private Integer age;
    private String token;

    private Boolean sendState;
    private String  sendStateMsg;
}
