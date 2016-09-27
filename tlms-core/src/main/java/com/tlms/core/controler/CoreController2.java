package com.tlms.core.controler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
//import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.tlms.core.domain.Test1;
import com.tlms.core.domain.Test2;
import com.tlms.core.service.IUserSerivice;
import com.tlms.core.servlce.impl.CoreServiceImpl2;

//@RequestMapping(value="/ActivitiSpring2")
@Controller
public class CoreController2{
	@Autowired
	private CoreServiceImpl2 coreServiceImpl3;
	@Autowired
	private IUserSerivice userService;
	
	/**
	 * rollback测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/coreRoleBackTest2.ctrl")
	public String coreRoleBackTest(){
		System.out.println("CoreController2->coreRoleBackTest  coreServiceImpl3:"+coreServiceImpl3);
		coreServiceImpl3.rollback_test();
		return "rollback_test";
	}
	
	@ResponseBody
	@RequestMapping(value="/userUpd.ctrl")
	public String userUpd(){
		System.out.println("CoreController2->userUpd");
		Test1 test1 = new Test1();
		test1.setId("1");
		test1.setCouse("数学");
		test1.setPoint("80");
		test1.setUserid("1");
//		userService.userUpd(test1);
		userService.userUpd(test1);
		return "rollback_test";
	}
	
	@ResponseBody
	@RequestMapping(value="/userUpd2.ctrl")
	public String userUpd2(){
		System.out.println("CoreController2->userUpd");
		Test2 test2 = new Test2();
//		test2.setId(UUID.randomUUID().toString());
		test2.setId("01693d86-3fb1-497b-a725-fde7dd349220");
		test2.setName("brighttang");
		test2.setSex("女");
		test2.setUserAddress("重庆市永川区大安街道");
		userService.userUpd2(test2);
		return "rollback_test";
	}
	
	@ResponseBody
	@RequestMapping(value="/userUpd3.ctrl")
	public String userUpd3(){
		System.out.println("CoreController2->userUpd");
		Test2 test2 = new Test2();
		test2.setId(UUID.randomUUID().toString());
//		test2.setId("01693d86-3fb1-497b-a725-fde7dd349220");
		test2.setName("brighttang");
		test2.setSex("女");
		test2.setUserAddress("重庆市永川区");
		userService.userUpd3(test2);
		return "rollback_test";
	}
	
	
}
