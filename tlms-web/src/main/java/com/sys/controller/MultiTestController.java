package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.mutidata.DatabaseContextHolder;
//import com.pujjr.pjrp.dubbo.service.IDubboService;
import com.pujjr.pjrp.dubbo.service.IPjrpService;
import com.pujjr.vo.PageVo;
import com.tlms.core.dao.Test1Mapper;
import com.tlms.core.dao.Test2Mapper;
import com.tlms.core.domain.Test1;
import com.tlms.core.domain.Test2;
import com.tlms.core.service.IMultiDataService;
import com.tlms.core.servlce.impl.MultiDataServiceImpl;

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
