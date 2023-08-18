package com.txxw.hr.service.impl;

import com.txxw.hr.dao.pojo.Role;
import com.txxw.hr.dao.mapper.RoleMapper;
import com.txxw.hr.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRolesByAdminId(Long adminId) {
        return roleMapper.getRolesByAdminId(adminId);
    }
}
