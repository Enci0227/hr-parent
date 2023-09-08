package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-22
 */
public interface IDepartmentService extends IService<Department> {

    Result getAllDepartments();

    Result addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    Result deleteDep(Long id);
}
