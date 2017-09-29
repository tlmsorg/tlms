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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tlms.core.service.IAuthService;
import com.tlms.core.service.IUserService;


@WebFilter
public class AuthFilter implements Filter{
	private static final Logger logger = Logger.getLogger(AuthFilter.class);
	@Value("${token.expire.time}")
	private long expireTime;
	@Autowired
	private IAuthService authServiceImpl;
	@Autowired
	private IUserService userServiceImpl;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("AuthFilter init");
		 ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		 IUserService demoBean = (IUserService)context.getBean("userServiceImpl");
		 logger.info("demoBean:"+demoBean);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter");
		
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		/*
		String token = servletRequest.getHeader("token");
		TokenVo tokenVo = new TokenVo();
		if(!"OPTIONS".equals(servletRequest.getMethod())){
			tokenVo = authServiceImpl.checkJwt(token);
		}
		request.setAttribute("userId", tokenVo.getUserId());
		request.setAttribute("passwd", tokenVo.getPasswd());
		
		servletResponse.setHeader("token", tokenVo.getToken());
		servletResponse.setHeader("expiretime", tokenVo.getExpireTime()+"");
*/
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

}
