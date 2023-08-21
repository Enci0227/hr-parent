package com.txxw.hr.handler;

import com.txxw.hr.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:统一异常处理
 **/
//对加了@Controller注解的方法进行拦截处理 AOP的实现
@RestControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据,不加的话相当于返回Result.fail(-999,"系统异常");页面了
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

    //SQL异常处理
    @ExceptionHandler(SQLException.class)
    public Result mySqlException(SQLException ex){
        if (ex instanceof SQLIntegrityConstraintViolationException){
            return Result.fail(-991,"该数据有关联数据，操作失败！");
        }
//        ex.printStackTrace();
        return Result.fail(-992,"数据库异常，操作失败！");
    }

}
