package com.example.mapper;

import com.example.entity.dto.RegistrationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class RegistrationMapperTest {
    @Resource
    RegistrationMapper registrationMapper;

    @Test
    void getActivityAndUserByAId() {

    /*    List<List<?>> lists = registrationMapper.getActivityUsersByAId(11L, "a", 0, 10);

        List<User> userList = (List<User>) lists.get(0);
        Long total = Long.valueOf(lists.get(1).get(0).toString());
        System.out.println("userList = " + userList);
        System.out.println("total = " + total);
//        System.out.println("registrationDTO = " + registrationDTO);
*/
    }

    @Test
    void getRegistrationAndUserByAId(){
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(11L, "a", 0, 10);
        List<RegistrationDTO> dtoList = (List<RegistrationDTO>) lists.get(0);
        Long total = Long.valueOf(lists.get(1).get(0).toString());
        for (RegistrationDTO registrationDTO : dtoList) {
            System.out.println("registrationDTO = " + registrationDTO);
        }
        System.out.println("total = " + total);
    }

    @Test
    void getRegistrationAndUserByAId2(){
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(11L,null,null,null);
        List<RegistrationDTO> dtoList = (List<RegistrationDTO>) lists.get(0);
        Long total = Long.valueOf(lists.get(1).get(0).toString());
        for (RegistrationDTO registrationDTO : dtoList) {
            System.out.println("registrationDTO = " + registrationDTO);
        }
        System.out.println("total = " + total);
    }
}