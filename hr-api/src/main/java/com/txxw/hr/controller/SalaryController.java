package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Salary;
import com.txxw.hr.service.ISalaryService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/")
    public Result addSalary(@RequestBody Salary salary){
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            return Result.success("添加成功！");
        }
        return Result.fail(60001,"添加失败！");
    }

    @ApiOperation(value = "删除工资账套")
    @DeleteMapping("/{id}")
    public Result deleteSalary(@PathVariable Integer id){
        if (salaryService.removeById(id)){
            return Result.success("删除成功！");
        }
        return Result.fail(60002,"删除失败！");
    }

    @ApiOperation(value = "更新工资账套")
    @PutMapping("/")
    public Result updateSalaryById(@RequestBody Salary salary){
        if (salaryService.updateById(salary)){
            return Result.success("更新成功！");
        }
        return Result.fail(60003,"更新失败！");
    }

}
