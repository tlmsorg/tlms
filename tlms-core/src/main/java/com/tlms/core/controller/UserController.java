package com.tlms.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.domain.SysUser;
import com.tlms.core.service.IAuthService;
import com.tlms.core.service.IUserService;
import com.tlms.core.servlce.impl.CoreServiceImpl2;
import com.tlms.core.vo.TokenVo;
import com.tlms.core.vo.User;

@RestController
public class UserController{
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private CoreServiceImpl2 coreServiceImpl3;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthService authServiceImpl;
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/service/user",method = RequestMethod.GET)
	public List<User> getAllUser(){
		List<User> userList = new ArrayList();
		User user = new User();
		user.setId("111");
		userList.add(user);
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/user/{id}",method = RequestMethod.GET)
	public User geUserById(@PathVariable String id){
		logger.debug("接收参数："+id);
		User user = new User();
		return user;
	}
	@ResponseBody
	@RequestMapping(value="/service/user/v1/{id}",method = RequestMethod.GET)
	public User geUserByIdV1(@PathVariable String id){
		logger.debug("接收参数："+id);
		User user = new User();
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/user/v2",method = RequestMethod.GET)
	public User geUserByIdV2(@RequestParam String id){
		logger.debug("接收参数："+id);
		User user = new User();
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/user/v3/{id}/{name}",method = RequestMethod.GET)
	public User geUserByIdV3(@PathVariable String id,@PathVariable String name){
		logger.debug("接收参数："+id+"|"+name);
		User user = new User();
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/user/v4/{id}/name/{name}",method = RequestMethod.GET)
	public User geUserByIdV4(@PathVariable String id,@PathVariable String name){
		logger.debug("接收参数："+id+"|"+name);
		User user = new User();
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/user/v5/{id}/name/{name}",method = RequestMethod.GET)
	public List<User> geUserByIdV5(@PathVariable String id,@PathVariable String name){
		logger.debug("接收参数："+id+"|"+name);
		List<User> userList = new ArrayList();
		User user = new User();
		user.setId("111");
		userList.add(user);
		return userList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/service/user",method = RequestMethod.POST)
	public void saveUser(@RequestBody User user){
		logger.debug(JSONObject.toJSONString(user));
	}
	

	@ResponseBody
	@RequestMapping(value="/user/signin",method = RequestMethod.POST)
	public JSONObject signIn(@RequestBody SysUser user,HttpServletResponse response) throws Exception{
		String strRet = "";
		JSONObject json = new JSONObject();
		if(userService.userSignIn(user.getUserId(), user.getPasswd())){
			strRet = "success";
			TokenVo tokenVo = authServiceImpl.createJwt(user);
			response.setHeader("token", tokenVo.getToken());
			response.setHeader("expiretime", tokenVo.getExpireTime()+"");
		}else{
			strRet = "failure";
			throw new Exception("用户登录失败");
		}
		json.put("result", strRet);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/service/signout",method = RequestMethod.POST)
	public void signOut(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String userId = request.getAttribute("userId")+"";
		response.setHeader("token", "");
	}
	
	
}
