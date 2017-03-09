package com.sys.controller;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sys.dao.SysUserMapper;
import com.sys.domain.SysUser;

/**
 * @author tom
 *
 */
@RestController
@ResponseBody
public class LoginController {
	@Autowired
	public SysUserMapper sysUserMapperImpl;
//	@RequestMapping(value="/userLogin",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public String userLogin(@RequestBody SysUser user) throws Exception{
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
		sysUserMapperImpl.insertSelective(sysUser);
		
//		return JSONObject.toJSONString(user2);
//		return user2;
		return "健康了减肥考了多少";

	}
}
