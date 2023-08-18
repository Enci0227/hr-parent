package com.txxw.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:配置前后端跨域访问
 **/
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    //    允许访问的端口设置，解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
        //本地测试 端口不一致 也算跨域
        //设置每一个接口可以被访问的域名
        registry.addMapping("/**").allowedOrigins("https://tianxunxingwang.com","http://localhost:8080");
    }
}
