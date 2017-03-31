package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pujjr.pjrp.dubbo.service.IDubboService;
import com.pujjr.pjrp.dubbo.service.IPjrpService;
import com.pujjr.vo.PageVo;

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
//		System.out.println(dubboServiceImpl.dubboService());
//		System.out.println(pjrpServiceImpl.selectCreditInfo("10001", "2012-01-01 15:21:31", "2017-05-01 15:21:31"));
		/*logger.info("消费者调用提供者服务");
		logger.info(dubboServiceImpl.dubboService());
		logger.info(pjrpServiceImpl.selectCreditInfo("10001", "2012-01-01 15:21:31", "2017-01-01 15:21:31"));*/
		String dubboServiceRet = pjrpServiceImpl.selectCreditInfo("10001", "2012-01-01 15:21:31", "2017-05-01 15:21:31");
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectCreditInfo/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectCreditInfo(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectCreditInfo");
		return pjrpServiceImpl.selectCreditInfo(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportCreditInfo/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportCreditInfo(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportCreditInfo");
		String dubboServiceRet = pjrpServiceImpl.exportCreditInfo(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectCollectionLog/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectCollectionLog(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectCollectionLog");
		return pjrpServiceImpl.selectCollectionLog(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportCollectionLog/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportCollectionLog(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportCollectionLog");
		String dubboServiceRet = pjrpServiceImpl.exportCollectionLog(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	
}
