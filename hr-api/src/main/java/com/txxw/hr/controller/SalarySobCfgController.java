package com.txxw.hr.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.txxw.hr.dao.pojo.Employee;
import com.txxw.hr.dao.pojo.Salary;
import com.txxw.hr.service.IEmployeeService;
import com.txxw.hr.service.ISalaryService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.ResultPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工账套
 */
@RestController
@RequestMapping("salary/sobcfg")
public class SalarySobCfgController {
    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工账套")
    @GetMapping("/")
    public ResultPage getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                            @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public Result updateEmployeeSalary(Long eid,Long sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

}
