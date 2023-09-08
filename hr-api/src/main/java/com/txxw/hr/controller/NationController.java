package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Nation;
import com.txxw.hr.dao.pojo.Position;
import com.txxw.hr.service.INationService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@Api(tags = "民族Controller")
@RestController
@RequestMapping("/nation")
public class NationController {

    @Autowired
    private INationService nationService;

    @ApiOperation(value = "获取所有民族信息")
    @GetMapping("/")
    public Result getAllNation() {
        List<Nation> list = nationService.list();
        return Result.success(list);
    }

}
