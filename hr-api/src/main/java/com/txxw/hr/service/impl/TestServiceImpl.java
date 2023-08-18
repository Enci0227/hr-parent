package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.txxw.hr.dao.mapper.TestMapper;
import com.txxw.hr.dao.pojo.Test;
import com.txxw.hr.service.TestService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.TestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:数据处理层接口实现
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Result findById(Long id) {
        LambdaQueryWrapper<Test> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Test::getId, id);
        Test test = testMapper.selectOne(wrapper);
        if (test == null) {
            return Result.fail(4000002,"未查找到该用户");
        }
        TestVo testVo = new TestVo();
        BeanUtils.copyProperties(test, testVo);

        return Result.success(testVo);
    }

    @Override
    public Result findOneById(Long id) {

        Test test = testMapper.findOneById(id);
        if (test == null) {
            return Result.fail(4000002,"未查找到该用户");
        }
        TestVo testVo = new TestVo();
        BeanUtils.copyProperties(test, testVo);

        return Result.success(testVo);
    }
}
