package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-08-17
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return
     */
    Result getAllMenus();
}
