package com.txxw.hr.service.impl;

import com.txxw.hr.dao.pojo.Admin;
import com.txxw.hr.service.IAdminService;
import com.txxw.hr.service.LoginService;
import com.txxw.hr.utils.JwtTokenUtil;
import com.txxw.hr.vo.ErrorCode;
import com.txxw.hr.vo.Result;
import com.txxw.hr.vo.params.LoginParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.txxw.hr.vo.ErrorCode.*;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/15
 * @description:
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登陆之后返回token
     *
     * @param adminLoginParam
     * @return
     */
    @Override
    public Result login(LoginParam adminLoginParam, HttpServletRequest request) {
//        String code = adminLoginParam.getCode();
//        System.out.println("第2个sessionId"+request.getSession().getId());
//        String captcha = (String) request.getSession().getAttribute("captcha");
////        System.out.println(captcha);
//        if (StringUtils.isBlank(code) || !captcha.equals(code)) {
//            return Result.fail(CAPTCHA_ERROR.getCode(),CAPTCHA_ERROR.getMsg());
//        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminLoginParam.getUsername());
        //获取不到用户信息或密码不匹配
        if (null == userDetails || !passwordEncoder.matches(adminLoginParam.getPassword(), userDetails.getPassword())) {
            return Result.fail(ACCOUNT_PWD_NOT_EXIST.getCode(), ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //账号被禁用
        if (!userDetails.isEnabled()) {
            return Result.fail(ACCOUNT_ENABLE.getCode(), ACCOUNT_ENABLE.getMsg());
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                , null, userDetails.getAuthorities());
        //配置security全局用户信息，放在security全局
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public Result logout() {
        return Result.success("退出成功！");
    }

    /**
     * 用户注册
     *
     * @param loginParam
     * @return
     */

    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1. 判断参数 是否合法
         * 2. 判断账户是否存在，存在 返回账户已经被注册
         * 3. 不存在，注册用户
         * 4. 生成token
         * 5. 存入redis 并返回
         * 6. 注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
         */
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
        ) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        Admin user = adminService.getAdminByUserName(username);
        System.out.println(user);
        if (user != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), "账户已经被注册了");
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setName("");
        admin.setPassword(passwordEncoder.encode(password));//spring-security密码加密
        admin.setLastLogin(System.currentTimeMillis());
        admin.setAvatar("");
        admin.setAdmin(true); //1 为true
        admin.setDeleted(false); // 0 为false
        admin.setSalt("");
        admin.setEnabled(true);
        admin.setEmail("");
        admin.setNotes("");
        admin.setMobilePhone("");
        this.adminService.saveUser(admin);
        //生成token
        String token = jwtTokenUtil.generateToken(admin);

//        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
