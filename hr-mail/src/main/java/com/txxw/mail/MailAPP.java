package com.txxw.mail;

import com.txxw.hr.dao.pojo.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:项目启动类
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailAPP {
    public static void main(String[] args) {
        SpringApplication.run(MailAPP.class, args);
    }

    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
}
