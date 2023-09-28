package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.dao.pojo.Role;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.service.IRoleService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
@Api(tags = "Admin用户(操作员)权限Controller")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取当前登录用户的信息",notes = "提供用户所有信息，包括角色及对应权限（无需传任何参数即可调用）")
    @GetMapping("/info")
    public Result getAdminInfo(Principal principal){
//        由于我们登录过之后已经把登录对象用户信息设置到Security全局中，因此可以直接使用Principal获取用户信息
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(roleService.getRolesByAdminId(admin.getId()));
        return Result.success(admin);
    }
    @ApiOperation(value = "超级管理员权限操作-根据用户名模糊获取所有操作员",notes = "此接口需要前端有专门的界面控制，获取的内容包含用户角色信息，不包含登录用户本身的信息")
    @GetMapping("/")
    public Result getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "超级管理员权限操作-更新操作员",notes = "此接口需要前端有专门的界面控制")
    @PutMapping("/")
    public Result updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "超级管理员权限操作-删除操作员",notes = "此接口需要前端有专门的界面控制")
    @DeleteMapping("/{id}")
    public Result deleteAdmin(@PathVariable Integer id){
        if (adminService.removeById(id)){
            return Result.success("删除成功！");
        }
        return Result.fail(60003,"删除失败！");
    }

    @ApiOperation(value = "超级管理员权限操作-获取所有角色",notes = "此接口需要前端有专门的界面控制")
    @GetMapping("/role")
    public List<Role> getAllRole(){
        return roleService.list();
    }

    @ApiOperation(value = "超级管理员权限操作-更新操作员角色",notes = "此接口需要前端有专门的界面控制")
    @PutMapping("/role")
    public Result updateAdminRole(Long adminId,Long[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }



}
