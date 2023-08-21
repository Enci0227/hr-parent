package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Position;
import com.txxw.hr.service.IPositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public Result getAllPositions() {
        List<Position> list = positionService.list();
        return Result.success(list);
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public Result addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return Result.success("添加成功!");
        }
        return Result.fail(60001,"添加失败!");
    }
    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public Result updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return Result.success("更新成功!");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public Result deletePosition(@PathVariable Long id) {
        if (positionService.removeById(id)) {
            return Result.success("删除成功!");
        }
        return Result.fail(60003,"删除失败!");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public Result deletePositionsByIds(Long[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return Result.success("删除成功!");
        }
        return Result.fail(60004,"删除失败!");
    }
}

