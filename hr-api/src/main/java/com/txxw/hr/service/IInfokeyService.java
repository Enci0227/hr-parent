package com.txxw.hr.service;

import com.txxw.hr.dao.pojo.Infokey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.InfokeyParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
public interface IInfokeyService extends IService<Infokey> {

    /**
     * 查询所有基本信息字段
     * @return
     */
    Result findAll();


}
