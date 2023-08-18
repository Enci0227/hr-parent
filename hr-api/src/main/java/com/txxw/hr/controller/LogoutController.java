package com.txxw.hr.controller;

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

@Api(tags = "LogoutController")
@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public Result logout(){
        return loginService.logout();
    }
}
