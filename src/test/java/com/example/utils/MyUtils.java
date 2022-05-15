package com.example.utils;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.util.Random;

public class MyUtils {
    public static void main(String[] args) {
        String now = DateUtil.now();
        System.out.println("now = " + now);
        //
        LocalDateTime time = LocalDateTime.of(2021, 11, 27, 0, 0);
        System.out.println("time = " + time);
        LocalDateTime days = time.plusDays(1);
        System.out.println("days = " + days);
        while (true) {
            if (time.getMonthValue()>=6)return;
        }
    }


    /**
     * @param s 小
     * @param e 大
     * @return
     */
    int getRandomInt(int s, int e) {
        return new Random().nextInt(e - 1 + s) + s;
    }
}
