package com.sys.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class CorsFilter implements Filter {
	private static final Logger logger = Logger.getLogger(CorsFilter.class);
	private String allowOrigin;  
    private String allowMethods;  
    private String allowCredentials;  
    private String allowHeaders;  
    private String exposeHeaders;  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("CorsFilter init");
		allowOrigin = filterConfig.getInitParameter("allowOrigin");  
        allowMethods = filterConfig.getInitParameter("allowMethods");  
        allowCredentials = filterConfig.getInitParameter("allowCredentials");  
        allowHeaders = filterConfig.getInitParameter("allowHeaders");  
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");  

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res; 
//        CollectionUtils.isEmpty(collection)
        //允许所有的域连接
        if(allowOrigin.equals("*")){
        	response.setHeader("Access-Control-Allow-Origin", "*"); 
        }else{
        	if (!StringUtils.isEmpty(allowOrigin)) {  
                List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));  
                if (!CollectionUtils.isEmpty(allowOriginList)) {  
                    String currentOrigin = request.getHeader("Origin");  
                    if (allowOriginList.contains(currentOrigin)) {  
                        response.setHeader("Access-Control-Allow-Origin", currentOrigin);  
                    }  
                }  
            } 
        }
       
        if (!StringUtils.isEmpty(allowMethods)) {  
            response.setHeader("Access-Control-Allow-Methods", allowMethods);  
        }  
        if (!StringUtils.isEmpty(allowCredentials)) {  
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);  
        }  
        if (!StringUtils.isEmpty(allowHeaders)) {  
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);  
        }  
        if (!StringUtils.isEmpty(exposeHeaders)) {  
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);  
        }  
        
        chain.doFilter(req, res);  
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
