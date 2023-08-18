package com.txxw.hr.dao.mapper;

import com.txxw.hr.dao.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Enci
 * @since 2023-08-17
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id 获取菜单列表
     * @param id
     */
    List<Menu> getMenusByAdminId(Long id);

    List<Menu> getMenusWithRole();
}
