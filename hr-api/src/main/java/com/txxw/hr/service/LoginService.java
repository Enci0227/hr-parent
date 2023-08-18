package com.txxw.hr.service;

import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.LoginParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/15
 * @description:
 **/
public interface LoginService {
    /**
     * 登录之后返回token
     * @param adminLoginParam
     * @return
     */
    Result login(LoginParam adminLoginParam, HttpServletRequest request);

    /**
     * 退出登录
     * @return
     */
    Result logout();

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
