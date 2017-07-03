package com.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.sys.service.IAuthService;

@WebFilter
public class AuthFilter implements Filter{
	@Value("${token.expire.time}")
	private long expireTimeOffset;
	@Autowired
	private IAuthService authServiceImpl;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter");
		
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		String token = servletRequest.getHeader("token");
		//验证token，如果验证通过，继续，并更新expireTime，如果验证未通过，返回结果，前端跳转重新登录页面。
//		authServiceImpl.checkJwt(token);
		//模拟已经验证通过，更新token超时时间：expireTime
		servletResponse.setHeader("expireTime", System.currentTimeMillis()+expireTimeOffset+"");
		servletResponse.setHeader("token", token);
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

}
