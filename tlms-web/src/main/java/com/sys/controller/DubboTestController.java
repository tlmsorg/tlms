package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.pujjr.pjrp.dubbo.service.IDubboService;
import com.pujjr.pjrp.dubbo.service.IPjrpService;
import com.pujjr.vo.PageVo;

/**
 * 测试报表系统
 * @author tom
 *
 */
@RestController
public class DubboTestController {
	private static final Logger logger = Logger.getLogger(DubboTestController.class);
	/*@Autowired
	private IDubboService dubboServiceImpl;*/
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
	
	@RequestMapping(value="/selectOverdueCollection/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectOverdueCollection(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectOverdueCollection(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportOverdueCollection/{time}",method=RequestMethod.GET)
	public String exportOverdueCollection(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportOverdueCollection(time);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectLedgerOfFdept/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectLedgerOfFdept(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectLedgerOfFdept(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportLedgerOfFdept/{time}",method=RequestMethod.GET)
	public String exportLedgerOfFdept(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportLedgerOfFdept(time);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectRecordLogOfFdept/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectRecordLogOfFdept(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectRecordLogOfFdept(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportRecordLogOfFdept/{time}",method=RequestMethod.GET)
	public String exportRecordLogOfFdept(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportRecordLogOfFdept(time);
		return dubboServiceRet;
	}
	
	
	@RequestMapping(value="/selectProgress/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectProgress(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectProgress");
		return pjrpServiceImpl.selectProgress(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportProgress/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportProgress(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportProgress");
		String dubboServiceRet = pjrpServiceImpl.exportProgress(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectMonthLoanData/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectMonthLoanData(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectMonthLoanData(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportMonthLoanData/{time}",method=RequestMethod.GET)
	public String exportMonthLoanData(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportMonthLoanData(time);
		return dubboServiceRet;
	}
	
	
	@RequestMapping(value="/selectLedgerOfRiskDept/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectLedgerOfRiskDept(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectLedgerOfRiskDept(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportLedgerOfRiskDept/{time}",method=RequestMethod.GET)
	public String exportLedgerOfRiskDept(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportLedgerOfRiskDept(time);
		return dubboServiceRet;
	}
	//------------------------
}
