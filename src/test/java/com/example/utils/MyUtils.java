package com.example.utils;

import cn.hutool.core.date.DateUtil;

public class MyUtils {
    public static void main(String[] args) {
        String now = DateUtil.now();
        System.out.println("now = " + now);
    }
}
