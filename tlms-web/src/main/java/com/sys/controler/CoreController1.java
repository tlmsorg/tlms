package com.sys.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.service.impl.CoreServiceImpl1;
import com.tlms.core.service.CoreServiceImpl2;

@Controller
public class CoreController1 {
	@Autowired
	private CoreServiceImpl1 coreServiceImpl;
	
	/**
	 * rollback测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/coreRoleBackTest1.ctrl")
	public String coreRoleBackTest(){
		System.out.println("CoreController1->coreRoleBackTest");
		coreServiceImpl.rollback_test();
		return "rollback_test";
	}
}
