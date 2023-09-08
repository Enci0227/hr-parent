package com.txxw.hr.config.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txxw.hr.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.txxw.hr.vo.ErrorCode.NO_PERMISSION;

/**
 * 当访问接口没有权限时，自定义返回结果
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Result result = Result.fail(NO_PERMISSION.getCode(),NO_PERMISSION.getMsg());
		out.write(new ObjectMapper().writeValueAsString(result));
		out.flush();
		out.close();
	}
}