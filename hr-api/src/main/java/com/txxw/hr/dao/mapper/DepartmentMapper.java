package com.txxw.hr.dao.mapper;

import com.txxw.hr.dao.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-22
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Long parentId);

    void addDep(Department dep);

    /**
     * 删除部门
     * @param dep
     */
    void deleteDep(Department dep);
}
