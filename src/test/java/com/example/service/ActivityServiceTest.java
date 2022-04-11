package com.example.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired


    @Test
    void list(){
        /*List<Activity> list = activityService.list();
        System.out.println("list = " + list);*/
    }

    @Test
    void save(){
        /*Activity activity = new Activity();
        activity.setName("活动2");
        activity.setContent("活动描述");
        activity.setUsername("关羽");
        activity.setNumber(150L);
        activity.setStarttime(new Date());
        activity.setEndtime(new Date());
        //
        boolean b = activityService.save(activity);
        System.out.println("b = " + b);*/
    }
}