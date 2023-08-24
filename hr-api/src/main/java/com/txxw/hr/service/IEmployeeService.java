package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.EmployeeParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工（分页）
     * @param employeeParams
     * @return
     */
    Result getEmployeeByPage(EmployeeParams employeeParams);

    Result maxWorkID();

    Result addEmp(Employee employee);

    /**
     * 查询员工
     * @param id
     * @return
     */
    List<Employee> getEmployee(Long id);
}
