package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.LoginParam;
import org.springframework.security.core.Authentication;

import java.util.List;

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

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    Result getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Result updateAdminRole(Long adminId, Long[] rids);

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    Result updateAdminPassword(String oldPass, String pass, Long adminId);

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    Result updateAdminUserFace(String url, Long id, Authentication authentication);

}
