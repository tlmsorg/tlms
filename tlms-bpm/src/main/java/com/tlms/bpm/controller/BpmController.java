package com.tlms.bpm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlms.bpm.service.IBpmService;

@RestController
@ResponseBody
public class BpmController {
	private static final Logger logger = Logger.getLogger(BpmController.class);
	@Autowired
	private IBpmService bpmServiceImpl;
	
	@RequestMapping(value="/service/process/deploy",method=RequestMethod.GET)
	public void processDeploy(HttpServletRequest request){
		logger.info(request.getAttribute("userId"));
		bpmServiceImpl.deployProcess();
	}
}
