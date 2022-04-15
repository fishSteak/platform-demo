package com.example.controller;

import com.example.common.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityControllerTest {

    @Autowired
    private ActivityController activityController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findPage() {
        Result<?> result = activityController.findPage("", false, 1, 10);
        System.out.println("result = " + result);
    }
}