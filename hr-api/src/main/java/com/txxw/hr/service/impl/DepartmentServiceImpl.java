package com.txxw.hr.service.impl;

import com.txxw.hr.dao.pojo.Department;
import com.txxw.hr.dao.mapper.DepartmentMapper;
import com.txxw.hr.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-22
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public Result getAllDepartments() {
        List<Department> allDepartments = departmentMapper.getAllDepartments((long) -1);
        return Result.success(allDepartments);
    }

    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public Result addDep(Department dep) {
        dep.setEnabled(true);
        //调用存储过程
        departmentMapper.addDep(dep);
        if (1==dep.getResult()){
            return Result.success(dep);
        }
        return Result.fail(60001,"添加失败！");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public Result deleteDep(Long id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2==dep.getResult()){
            return Result.fail(60005,"该部门下还有子部门，删除失败！");
        }
        if (-1==dep.getResult()){
            return Result.fail(60006,"该部门下还有员工，删除失败！");
        }
        if (1==dep.getResult()){
            return Result.success("删除成功！");
        }
        return Result.fail(60003,"删除失败！");
    }
}
