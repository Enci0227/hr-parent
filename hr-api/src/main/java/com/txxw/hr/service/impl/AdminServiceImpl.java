package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txxw.hr.dao.mapper.AdminRoleMapper;
import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.dao.mapper.AdminMapper;
import com.txxw.hr.dao.pojo.AdminRole;
import com.txxw.hr.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.utils.AdminUtils;
import com.txxw.hr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    /**
     * 获取登录用户名
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public void saveUser(Admin admin) {
        //保存用户这 id会自动生成
        //这个地方 默认生成的id是 分布式id 雪花算法
        //mybatis-plus
        this.adminMapper.insert(admin);
    }

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @Override
    public Result getAllAdmins(String keywords) {
        List<Admin> allAdmins = adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
        return Result.success(allAdmins);
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional  //要删除然后添加 需要事务，万一失败还可以回滚
    public Result updateAdminRole(Long adminId, Long[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length==result){
            return Result.success("更新成功!");
        }
        return Result.fail(60002,"更新失败！");
    }

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    @Override
    public Result updateAdminPassword(String oldPass, String pass, Long adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断旧密码是否正确
        if (encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (1==result){
                return Result.success("修改用户密码成功！");
            }
        }else{
            return Result.fail(60002,"旧密码不匹配，修改用户密码失败！");
        }
        return Result.fail(60002,"修改用户密码失败！");
    }

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    @Override
    public Result updateAdminUserFace(String url, Long id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setAvatar(url);
        int result = adminMapper.updateById(admin);
        if (1==result){
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setAvatar(url);
            SecurityContextHolder.getContext().setAuthentication
                    (new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return Result.success(url);
        }
        return Result.fail(60002,"更新失败！");
    }


}
