package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Department;
import com.txxw.hr.service.IDepartmentService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-22
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public Result getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public Result addDep(@RequestBody Department dep){
        return departmentService.addDep(dep);
    }
    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public Result deleteDep(@PathVariable Long id){
        return departmentService.deleteDep(id);
    }

}
