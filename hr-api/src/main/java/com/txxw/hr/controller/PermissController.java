package com.txxw.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txxw.hr.dao.pojo.Menu;
import com.txxw.hr.dao.pojo.MenuRole;
import com.txxw.hr.dao.pojo.Role;
import com.txxw.hr.service.IMenuRoleService;
import com.txxw.hr.service.IMenuService;
import com.txxw.hr.service.IRoleService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/21
 * @description:权限组
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public Result getAllRoles(){
        List<Role> list = roleService.list();
        return Result.success(list);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public Result addRole(@RequestBody Role role){
        //springsecurity判断的时候需要ROLE_关键字
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)){
            return Result.success("添加成功！");
        }
        return Result.fail(60001,"添加失败！");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public Result deleteRole(@PathVariable Long rid){
        if (roleService.removeById(rid)){
            return Result.success("删除成功！");
        }
        return Result.fail(60002,"删除失败！");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public Result getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public Result getMidByRid(@PathVariable Long rid){
        List<Long> ridlist = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream()
                .map(MenuRole::getMid).collect(Collectors.toList());
        return Result.success(ridlist);
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public Result updateMenuRole(Long rid,Long[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }
}