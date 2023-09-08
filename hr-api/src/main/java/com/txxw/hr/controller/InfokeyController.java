package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.service.IInfokeyService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.InfokeyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 天巡十问有关基本信息部分的管理
 * @author Enci
 * @since 2023-09-01
 */
@Api(tags = "天巡十问基本信息Controller")
@RestController
@RequestMapping("/infokey")
public class InfokeyController {

    @Autowired
    private IInfokeyService infokeyService;

    @ApiOperation(value = "查询所有可用的基本信息字段",notes = "枚举出所有基本信息字段，可用于问卷基本信息的新增字段")
    @GetMapping("list")
    public Result Infokeys(){
        return infokeyService.findAll();
    }

}
