package com.txxw.hr.controller;

import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.utils.FastDFSUtils;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.PasswordParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 个人中心
 */
@Api(tags = "登录用户信息Controller")
@RestController
public class AdminInfoController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前登录用户信息（暂不启用）",notes = "authentication不传（无需此参数），只传admin用户信息")
    @PutMapping("/admin/info")
    public Result updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if (adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication
                    (new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));

            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "更新登录用户密码")
    @PutMapping("/admin/pass")
    public Result updateAdminPassword(@RequestBody PasswordParam passwordParam){
        String oldPass = passwordParam.getOldPass();
        String pass = passwordParam.getPass();
        Long adminId = passwordParam.getAdminId();
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }

    @ApiOperation(value = "更新当前登录用户头像（暂不启用）")
    @PostMapping("/admin/userface")
    public Result updateAdminUserFace(MultipartFile file,Long id,Authentication authentication){
        String[] filePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl() + filePath[0] + "/" +filePath[1];
        return adminService.updateAdminUserFace(url,id,authentication);
    }

    @ApiOperation(value = "更新当前登录用户基本信息",notes = "authentication不传（无需此参数），只传admin用户基本信息(这里暂定只更新姓名（昵称）、手机号、头像、邮箱)")
    @PutMapping("/admin/updateinfo")
    public Result updateAdminBaseInfo(@RequestBody Admin admin, Authentication authentication){
        Admin admin1 = new Admin();
        admin1.setId(admin.getId());
        admin1.setName(admin.getName());
        admin1.setMobilePhone(admin.getMobilePhone());
        admin1.setAvatar(admin.getAvatar());
        admin1.setEmail(admin.getEmail());
        admin1.setNotes(admin.getNotes());
        if (adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication
                    (new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }
}
