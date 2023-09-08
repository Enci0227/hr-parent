package com.txxw.hr.service.impl;

import com.txxw.hr.dao.mapper.AdminMapper;
import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.dao.pojo.Menu;
import com.txxw.hr.dao.mapper.MenuMapper;
import com.txxw.hr.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.utils.AdminUtils;
import com.txxw.hr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户id 获取菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        //获取用户id
        Long adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从Redis中获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_" + adminId, menus);
        }

        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单（权限组）
     * @return
     */
    @Override
    public Result getAllMenus() {
        List<Menu> allMenus = menuMapper.getAllMenus();
        return Result.success(allMenus);
    }
}
