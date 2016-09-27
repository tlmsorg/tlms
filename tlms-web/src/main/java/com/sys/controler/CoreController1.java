package com.sys.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.service.ICoreServiceImpl1;
import com.sys.service.impl.CoreServiceImpl1;

@Controller
public class CoreController1 {
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
	@RequestMapping(value="/coreRoleBackTest1.ctrl")
	public String coreRoleBackTest(){
		System.out.println("CoreController1->coreRoleBackTest");
		
		coreServiceImpl.rollback_test();
		coreServiceImpl3.rollback_test();
		System.out.println(coreServiceImpl);
		System.out.println(coreServiceImpl3);
		return "rollback_test";
	}
}
