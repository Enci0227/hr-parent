package com.txxw.hr.config.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txxw.hr.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.txxw.hr.vo.ErrorCode.NO_LOGIN;

/**
 * 当未登录或者token失效时访问接口时，自定义的返回结果
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Result result = Result.fail(NO_LOGIN.getCode(),NO_LOGIN.getMsg());
		out.write(new ObjectMapper().writeValueAsString(result));
		out.flush();
		out.close();
	}
}