package com.example.utils;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.entity.dto.PushPlusData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

@Slf4j
public class MsgTools {
    public static Result<?> sendPushPlusMsg(PushPlusData data) {
        try {
            Connection.Response response = Jsoup.connect("http://pushplus.hxtrip.com/send")
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .requestBody(JSON.toJSONString(data))
                    .header("Content-Type", "application/json")
                    .execute();
            int code = response.statusCode();
//            log.debug("消息发送成功,返回code = " + code);
            return Result.success(code + "", null, "消息处理成功,返回code = " + code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
