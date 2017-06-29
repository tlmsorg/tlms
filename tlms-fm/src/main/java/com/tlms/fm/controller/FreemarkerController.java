package com.tlms.fm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tlms.fm.User;

import com.alibaba.dubbo.remoting.exchange.Request;

@Controller
@RequestMapping(value="/home")
public class FreemarkerController {
	@RequestMapping(value="/index")
	public ModelAndView Add(HttpServletRequest request,HttpServletResponse resonse){
		ModelAndView mav = new ModelAndView("");
		User user = new User();
		user.setUserName("brighttang");
		user.setPassWord("123456");
		user.setMan(false);
		List<User> users  = new ArrayList<User>();
        users.add(user);

		mav.addObject("users", users);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value="/userQuery")
	public String userQuery(HttpServletRequest request,HttpServletResponse resonse){
//	public ModelAndView userQuery(HttpServletRequest request,HttpServletResponse resonse){
		ModelAndView mav = new ModelAndView("");
		User user = new User();
		user.setUserName("brighttang");
		user.setPassWord("123456");
		List<User> users  = new ArrayList<User>();
        users.add(user);

		mav.addObject("users", users);
		mav.setViewName("base/userQuery");
//		return mav;
		return "base/userQuery";
	}
	
}
