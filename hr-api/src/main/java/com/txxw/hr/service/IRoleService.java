package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-18
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Long adminId);


}
