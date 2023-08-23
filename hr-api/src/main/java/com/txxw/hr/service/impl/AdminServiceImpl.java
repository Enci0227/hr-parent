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


}
