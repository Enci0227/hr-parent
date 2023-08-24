package com.txxw.hr.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txxw.hr.dao.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工(分页)
     * @param page
     * @param employee
     * @param beginDateScope
     */
    IPage<Employee> getEmployeeByPage(@Param("page") Page<Employee> page,
                                      @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Long id);
}
