package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-22
 */
public interface IMenuRoleService extends IService<MenuRole> {


    Result updateMenuRole(Long rid, Long[] mids);
}
