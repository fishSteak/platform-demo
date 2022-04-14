package com.example.mapper;

import com.example.entity.User;
import com.example.entity.dto.RegistrationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RegistrationMapperTest {
    @Resource
    RegistrationMapper registrationMapper;

    @Test
    void getActivityAndUserByAId() {

        List<List<?>> lists = registrationMapper.getActivityAndUserByAId(11L, "a", 0, 10);

        List<User> userList = (List<User>) lists.get(0);
        Long total = Long.valueOf(lists.get(1).get(0).toString());
        System.out.println("userList = " + userList);
        System.out.println("total = " + total);
//        System.out.println("registrationDTO = " + registrationDTO);

    }
}