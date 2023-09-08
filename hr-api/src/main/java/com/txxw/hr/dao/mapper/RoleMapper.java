package com.txxw.hr.dao.mapper;

import com.txxw.hr.dao.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-18
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByAdminId(Long adminId);
}
