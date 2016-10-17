package com.tlms.push.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlms.push.service.aopcontexttest.IService;
import com.tlms.push.service.aopcontexttest.Service1;

@Controller
public class AopContextController {
	private Logger logger = Logger.getLogger("AopContextController.class");
	@Autowired
	private IService service1;
	@Autowired 
	private IService service2;
	@ResponseBody
	@RequestMapping(value="/aopControllerTest.ctrl")
	public String aopControllerTest(){
		logger.debug("AopContextController->aopControllerTest service1:"+service1);
		service1.test();
//		service2.test();
		return"";
	}
}
