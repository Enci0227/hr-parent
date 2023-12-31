package com.txxw.hr.controller;

import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.utils.FastDFSUtils;
import com.txxw.hr.vo.Result;
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
@RestController
public class AdminInfoController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public Result updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if (adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication
                    (new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));

            return Result.success("更新成功！");
        }
        return Result.fail(60002,"更新失败！");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public Result updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }

    @ApiOperation(value = "更新用户头像")
    @PostMapping("/admin/userface")
    public Result updateAdminUserFace(MultipartFile file,Long id,Authentication authentication){
        String[] filePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl() + filePath[0] + "/" +filePath[1];
        return adminService.updateAdminUserFace(url,id,authentication);
    }
}
