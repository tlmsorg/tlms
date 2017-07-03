package com.sys.controller;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sys.dao.SysUserMapper;
import com.sys.domain.SysUser;
import com.sys.service.IAuthService;

/**
 * @author tom
 *
 */
@RestController
@ResponseBody
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	public SysUserMapper sysUserMapperImpl;
	@Autowired
	public IAuthService authServiceImpl;
//	@RequestMapping(value="/userLogin",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public SysUser userLogin(@RequestBody SysUser user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String strRet = "健康了减肥考了多少";
//		System.out.println("user"+user.getName());
		String str = null;
		/*if(str.equals("111")){
			System.out.println("tttt");
		}*/
	/*	if(true){
			throw new RuntimeException("就困了就哭了就分开了倒计时了分解机房顶上");
		}*/
		
		/*User newUser = new User();
		newUser.setAge(1);
		newUser.setBirthday(new Date());
		newUser.setId("1");
		newUser.setCity("1");
		newUser.setDetail("1");
		newUser.setDistrict("1");
		newUser.setName("1");
		newUser.setProvince("1");
		newUser.setSex("1");
		newUser.setUserid("1");
		userMapperImpl.insertSelective(newUser);*/
		
		/*Test1 test1 = new Test1();
		test1.setId("11111");
		test1MapperImpl.insertSelective(test1);*/
		
		/*Test2 test2 = new Test2();
		test2.setId("1111");
		test2MapperImpl.insertSelective(test2);*/
		
		
		/*Student stu = new Student();
		stu.setUserId("434343");
		stu.setCouse("1");
		stu.setPoint("555");
		studentMapperImpl.stuAdd(stu);*/
		
		SysUser sysUser = new SysUser();
		sysUser.setId("111");
//		sysUserMapperImpl.insertSelective(sysUser);
		String jwt = authServiceImpl.createJwt();
		authServiceImpl.checkJwt(jwt);
		
//		response.addHeader("X-Token", jwt);
		response.setHeader("token", jwt);
		long serverTime = System.currentTimeMillis();
		response.setHeader("expireTime", serverTime+3*60*1000+"");
//		response.addHeader("Access-Control-Expose-Header", "Server");
//		response.setHeader("Access-Control-Expose-Headers", "Server,token");
		
		return sysUser;
//		return user2;
//		return "健康了减肥考了多少";
//		return JSONObject.toJSONString(sysUser);

	}
	
	@RequestMapping(value="/doTrans",method=RequestMethod.POST)
	public String doTrans(@RequestBody SysUser tranData,HttpServletRequest request){
		String strRet = "";
		System.out.println("toTrans:"+tranData);
		String tokenRcv = request.getHeader("token");
		String expireTime = request.getHeader("expireTime");
		Long exTimeMillis = Long.parseLong(expireTime);
		Long nowTimeMillis = System.currentTimeMillis();
		if(exTimeMillis < nowTimeMillis){
			System.out.println("exTimeMillis:"+exTimeMillis+",nowTimeMillis:"+nowTimeMillis+",cookie失效");
			strRet = "{\"msg\":\"登录超时，请重新登录\"}";
		}else{
			authServiceImpl.checkJwt(tokenRcv);
			System.out.println("tokenRcv:"+tokenRcv);
			
			System.out.println("exTimeMillis:"+exTimeMillis+",nowTimeMillis:"+nowTimeMillis+",cookie有效");
			strRet = "{\"msg\":\"交易成功\"}";
		}
		return strRet;
	}
}
