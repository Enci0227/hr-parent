package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.LoginParam;

/**
 * <p>
 *  Admin服务类实现层
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
public interface IAdminService extends IService<Admin> {


    Admin getAdminByUserName(String username);

    /**
     * 保存用户
     *
     * @param admin
     */
    void saveUser(Admin admin);

}
