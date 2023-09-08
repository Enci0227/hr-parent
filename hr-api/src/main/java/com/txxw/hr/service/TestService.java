package com.txxw.hr.service;

import com.txxw.hr.vo.Result;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:数据处理层接口
 **/
public interface TestService {
    Result findById(Long id);
    Result findOneById(Long id);
}
