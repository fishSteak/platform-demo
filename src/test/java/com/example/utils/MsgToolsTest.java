package com.example.utils;

import com.alibaba.fastjson2.JSON;
import com.example.common.Result;
import com.example.entity.dto.PushPlusData;
import org.junit.jupiter.api.Test;

class MsgToolsTest {

    @Test
    void sendPushPlusMsg() {
        PushPlusData data = new PushPlusData("title", "内容1", "75b8a58831b04410a2c185f27f20ac59");
        Result<?> result = MsgTools.sendPushPlusMsg(data);
        String string = JSON.toJSONString(result);
        System.out.println("string = " + string);
    }
}