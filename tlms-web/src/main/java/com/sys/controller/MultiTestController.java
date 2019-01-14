package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tlms.core.service.IMultiDataService;

/**
 * 多数据源测试
 * @author tom
 *
 */
@RestController
public class MultiTestController {
	private static final Logger logger = Logger.getLogger(MultiTestController.class);

	@Autowired
	private IMultiDataService multiDataServiceImpl;
	@RequestMapping(value="/selectMultiData",method=RequestMethod.GET)
	public String selectMultiData(){
		multiDataServiceImpl.userQuery();
		return "";
	}
	
}
