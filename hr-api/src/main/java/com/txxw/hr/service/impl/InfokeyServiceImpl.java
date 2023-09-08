package com.txxw.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.dao.mapper.InfokeyMapper;
import com.txxw.hr.service.IInfokeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.InfokeyParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
@Service
public class InfokeyServiceImpl extends ServiceImpl<InfokeyMapper, Infokey> implements IInfokeyService {

    @Autowired
    private InfokeyMapper infokeyMapper;

    @Override
    public Result findAll() {
        List<Infokey> infokeys = infokeyMapper.selectList(null);
        return Result.success(infokeys);
    }

}
