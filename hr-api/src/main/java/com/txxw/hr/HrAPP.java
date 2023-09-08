package com.txxw.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:项目启动类
 **/
@SpringBootApplication
//@EnableScheduling
public class HrAPP {
    public static void main(String[] args) {
        SpringApplication.run(HrAPP.class, args);
    }
}
