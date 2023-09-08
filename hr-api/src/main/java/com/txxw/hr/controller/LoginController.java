package com.txxw.hr.controller;

import com.txxw.hr.service.IAdminService;
import com.txxw.hr.service.LoginService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "登录Controller")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam, HttpServletRequest request){
        //登录 验证用户  访问用户表，但是
        return loginService.login(loginParam,request);
    }

}
