package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pujjr.pjrp.dubbo.service.IDubboService;
import com.pujjr.pjrp.dubbo.service.IPjrpService;

@RestController
public class DubboTestController {
	private static final Logger logger = Logger.getLogger(DubboTestController.class);
	@Autowired
	private IDubboService dubboServiceImpl;
	@Autowired
	private IPjrpService pjrpServiceImpl;
	@RequestMapping(value="/consumer",method=RequestMethod.GET)
	public String dubboConsumerTest(){
		
		System.out.println("消费者调用提供者服务");
		System.out.println(dubboServiceImpl.dubboService());
		System.out.println(pjrpServiceImpl.selectCreditInfo("10001", "2012-01-01 15:21:31", "2017-01-01 15:21:31"));
		/*logger.info("消费者调用提供者服务");
		logger.info(dubboServiceImpl.dubboService());
		logger.info(pjrpServiceImpl.selectCreditInfo("10001", "2012-01-01 15:21:31", "2017-01-01 15:21:31"));*/
		String str = "dubboConsumerTest 返回";
		return str;
	}
}
