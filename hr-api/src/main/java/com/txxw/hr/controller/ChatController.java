package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天，可以和谁聊天
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public Result getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

}
