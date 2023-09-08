package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Annex;
import com.txxw.hr.service.ITestpaperService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.InfokeyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-09-07
 */
@Api(tags="天巡十问问卷表单Controller")
@RestController
@RequestMapping("/txsw/testpaper")
public class TestpaperController {

    @Autowired
    private ITestpaperService testpaperService;

    @ApiOperation(value = "根据求职来源获取天巡十问问卷")
    @GetMapping("/{id}")
    public Result getTestpaper(@PathVariable @ApiParam(value = "问卷编号(1社招2校招3实习)", required = true) Long id ){

        return testpaperService.getTestpaper(id);
    }

    @ApiOperation(value = "更新天巡十问问卷基本信息表单")
    @PostMapping("update")
    public Result updateInfokeyForm(@RequestBody @ApiParam(value = "更新基本信息表单参数", required = true) InfokeyParam infokeyParam){
        return testpaperService.updateInfokeyForm(infokeyParam);
    }

}
