package com.example.common.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataAutoFill implements MetaObjectHandler {

//    private final JwtTokenConfig jwtTokenConfig;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("time", DateUtil.now(), metaObject);
//        this.setFieldValByName("createdBy", jwtTokenConfig.getUserIdFromRequest(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
//        this.setFieldValByName("updatedBy", jwtTokenConfig.getUserIdFromRequest(), metaObject);
    }
}
