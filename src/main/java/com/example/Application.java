package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.warn("账号：admin   密码：admin");
        log.warn("后台项目：项目启动后请访问：http://localhost:80/page/end/login.html");
        log.warn("前台项目：项目启动后请访问：http://localhost:80/page/front/frame.html");
    }
}
