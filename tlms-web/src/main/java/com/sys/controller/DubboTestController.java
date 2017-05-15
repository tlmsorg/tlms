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
 * 报表系统测试客户端
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
	
	@RequestMapping(value="/selectBranchProductSortCountData/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectBranchProductSortCountData(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectBranchProductSortCountData(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportBranchProductSortCountData/{time}",method=RequestMethod.GET)
	public String exportBranchProductSortCountData(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportBranchProductSortCountData(time);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/selectBranchOverdueData/{time}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectBranchOverdueData(@PathVariable String time,@PathVariable String pageNum,@PathVariable String pageSize){
		return pjrpServiceImpl.selectBranchOverdueData(time, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportBranchOverdueData/{time}",method=RequestMethod.GET)
	public String exportBranchOverdueData(@PathVariable String time){
		String dubboServiceRet = pjrpServiceImpl.exportBranchOverdueData(time);
		return dubboServiceRet;
	}
	//20170418 add  上海资信上报数据  + 银联入账
	@RequestMapping(value="/selectShzxLoanApplyInfo/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectShzxLoanApplyInfo(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectShzxLoanApplyInfo");
		return pjrpServiceImpl.selectShzxLoanApplyInfo(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportShzxLoanApplyInfo/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportShzxLoanApplyInfo(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportShzxLoanApplyInfo");
		String dubboServiceRet = pjrpServiceImpl.exportShzxLoanApplyInfo(timeBegin, timeEnd);
		return dubboServiceRet;
	}

	@RequestMapping(value="/selectShzxLivingInfo/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectShzxLivingInfo(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectShzxLivingInfo");
		return pjrpServiceImpl.selectShzxLivingInfo(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportShzxLivingInfo/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportShzxLivingInfo(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportShzxLivingInfo");
		String dubboServiceRet = pjrpServiceImpl.exportShzxLivingInfo(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	

	@RequestMapping(value="/selectShzxIdInfo/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectShzxIdInfo(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectShzxIdInfo");
		return pjrpServiceImpl.selectShzxIdInfo(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportShzxIdInfo/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportShzxIdInfo(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportShzxIdInfo");
		String dubboServiceRet = pjrpServiceImpl.exportShzxIdInfo(timeBegin, timeEnd);
		return dubboServiceRet;
	}

	@RequestMapping(value="/selectShzxCareerInfo/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectShzxCareerInfo(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectShzxCareerInfo");
		return pjrpServiceImpl.selectShzxCareerInfo(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportShzxCareerInfo/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportShzxCareerInfo(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportShzxCareerInfo");
		String dubboServiceRet = pjrpServiceImpl.exportShzxCareerInfo(timeBegin, timeEnd);
		return dubboServiceRet;
	}

	@RequestMapping(value="/selectUnionPayRecord/{timeBegin}/{timeEnd}/{pageNum}/{pageSize}",method=RequestMethod.GET)
	public PageVo selectUnionPayRecord(@PathVariable String timeBegin,@PathVariable String timeEnd,@PathVariable String pageNum,@PathVariable String pageSize){
		System.out.println("消费者调用提供者服务*****selectUnionPayRecord");
		return pjrpServiceImpl.selectUnionPayRecord(timeBegin, timeEnd, pageNum, pageSize);
	}
	
	@RequestMapping(value="/exportUnionPayRecord/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportUnionPayRecord(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		System.out.println("消费者调用提供者服务******exportUnionPayRecord");
		String dubboServiceRet = pjrpServiceImpl.exportUnionPayRecord(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	
	@RequestMapping(value="/exportIncomeLog/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportIncomeLog(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		String dubboServiceRet = pjrpServiceImpl.exportIncomeLog(timeBegin, timeEnd);
		return dubboServiceRet;
	}

	@RequestMapping(value="/exportProbationBranchCredit/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportProbationBranchCredit(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		String dubboServiceRet = pjrpServiceImpl.exportProbationBranchCredit(timeBegin, timeEnd);
		return dubboServiceRet;
	}
	@RequestMapping(value="/exportZdwUploadData/{timeBegin}/{timeEnd}",method=RequestMethod.GET)
	public String exportZdwUploadData(@PathVariable String timeBegin,@PathVariable("timeEnd") String timeEnd){
		String dubboServiceRet = pjrpServiceImpl.exportZdwUploadData(timeBegin, timeEnd);
		return dubboServiceRet;
	}
}
