package com.sys.controller;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sys.domain.User;

/**
 * @author tom
 *
 */
@RestController
public class LoginController {
	@ResponseBody
	@RequestMapping(value="/userLogin",method=RequestMethod.GET)
	public String userLogin(@RequestBody User user){
		String strRet = "55555";
		System.out.println("66666");
		return strRet;
	}
}
