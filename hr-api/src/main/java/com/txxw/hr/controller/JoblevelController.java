package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Joblevel;
import com.txxw.hr.dao.pojo.Position;
import com.txxw.hr.service.IJoblevelService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-21
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称信息")
    @GetMapping("/")
    public Result getAllJobLevels() {
        List<Joblevel> list = joblevelService.list();
        return Result.success(list);
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public Result addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return Result.success("添加成功!");
        }
        return Result.fail(60001,"添加失败!");
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public Result updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return Result.success("更新成功!");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public Result deleteJobLevel(@PathVariable Long id) {
        if (joblevelService.removeById(id)) {
            return Result.success("删除成功!");
        }
        return Result.fail(60003,"删除失败!");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public Result deleteJobLevelByIds(Long[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return Result.success("删除成功!");
        }
        return Result.fail(60004,"删除失败!");
    }

}
