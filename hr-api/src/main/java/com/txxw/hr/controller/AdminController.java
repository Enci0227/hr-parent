package com.txxw.hr.controller;


import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.dao.pojo.Role;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.service.IRoleService;
import com.txxw.hr.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取当前登录用户的信息")
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
    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public Result getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public Result updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public Result deleteAdmin(@PathVariable Integer id){
        if (adminService.removeById(id)){
            return Result.success("删除成功！");
        }
        return Result.fail(60003,"删除失败！");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/role")
    public List<Role> getAllRole(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public Result updateAdminRole(Long adminId,Long[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }



}
