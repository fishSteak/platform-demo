package com.example.mapper;

import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    void getUsersByRegistration() {
        List<User> list = userMapper.getUsersByRegistration(11L, true);
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }
}