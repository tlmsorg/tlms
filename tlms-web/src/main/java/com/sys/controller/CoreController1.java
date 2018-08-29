package com.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sys.service.ICoreServiceImpl1;

//@Controller
@RestController
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
	
	/*@Autowired
	private IPjMessageService pjMessageServiceImpl;
	*/
	/*@ResponseBody
	@RequestMapping(value="/push/{accountId}/{messageType}/{message}")
	public void pushMessage(@PathVariable String accountId,@PathVariable String messageType,@PathVariable String message){
		System.out.println("PjMessageTestController--->pushMessage");
		pjMessageServiceImpl.pushMessage(accountId, messageType, message);
	}*/
}
