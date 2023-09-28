package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Nation;
import com.txxw.hr.dao.pojo.PoliticsStatus;
import com.txxw.hr.service.IPoliticsStatusService;
import com.txxw.hr.vo.Result;
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
@RestController
@RequestMapping("/politics-status")
public class PoliticsStatusController {

    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @ApiOperation(value = "获取所有政治面貌选项")
    @GetMapping("/")
    public Result getAllPoliticsStatus() {
        List<PoliticsStatus> list = politicsStatusService.list();
        return Result.success(list);
    }

}
