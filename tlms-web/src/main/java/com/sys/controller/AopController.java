package com.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.utils.TransactionMapData;
import com.sys.service.IAopTest;
import com.sys.service.ICoreServiceImpl1;
import com.sys.service.impl.AopTestImpl;
import com.sys.service.impl.CoreServiceImpl1;

@Controller
public class AopController {
	@Autowired
	private IAopTest aopTestImpl;
	
	@Autowired
	@Qualifier("coreService1")
	private ICoreServiceImpl1 coreServiceImpl;
	@Autowired
	@Qualifier("coreService2")
	
	
	private ICoreServiceImpl1 coreServiceImpl3;
	
	/**
	 * rollback测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/aopTest",method=RequestMethod.GET)
	public String aopTest(){
		System.out.println("aopTest");
		TransactionMapData tmd = TransactionMapData.getInstance();
		tmd.put("name", "唐亮");
//		aopTestImpl.aopTest(tmd);
		return "aopController";
	}
	
	/*@RequestMapping(value="/aopTest",method=RequestMethod.GET)
	public String aopTest(){
		pjrpServiceImpl.aopTest();
		String str = "dubboConsumerTest 返回";
		return str;
	}*/
}
