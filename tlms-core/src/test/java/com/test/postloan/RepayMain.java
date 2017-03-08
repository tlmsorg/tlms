package com.test.postloan;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.activiti.bpmn.converter.CallActivityXMLConverter;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.enumeration.EIntervalMode;
import com.tlms.core.util.Utils;

/**
 * @author tom
 *
 */
public class RepayMain implements Itest{

	
	/**获取应还未还利息（含当前还款周期）
	 * tom 2016年11月9日
	 * @param shouldList
	 * @param repayDate
	 * @return
	 */
	public double getShouldInterest(List<ShouldPayBean> shouldList,Date repayDate){
		BigDecimal shouldInterest = new BigDecimal(0);
		Calendar repayCl = Calendar.getInstance();
		repayCl.setTime(repayDate);
	    repayCl.add(Calendar.MONTH, 1);
		for (ShouldPayBean shouldPayBean : shouldList) {
			Date dueDate = shouldPayBean.getDueDate();
			if(!shouldPayBean.isSettle() && dueDate.before(repayCl.getTime())){
				shouldInterest = shouldInterest.add(new BigDecimal(shouldPayBean.getInterest()));
			}
		}
		System.out.println("应还未还利息："+Utils.formateDouble2Double(shouldInterest, 2));
		return Utils.formateDouble2Double(shouldInterest, 2);
	}
	/**
	 * 获取总罚息
	 * tom 2016年11月9日
	 * @param shouldList
	 * @param repayDate
	 * @return
	 */
	public double getFineAmt(List<ShouldPayBean> shouldList,Date repayDate){
		BigDecimal fineAmt = new BigDecimal(0);
		for (ShouldPayBean shouldPayBean : shouldList) {
			Date currDueDate = shouldPayBean.getDueDate();
			if(repayDate.after(currDueDate) && !shouldPayBean.isSettle()){//计算罚息
				//罚息计息天数
				long dayOfFine = Utils.getTimeInterval(shouldPayBean.getDueDate(), repayDate, EIntervalMode.DAYS);
				BigDecimal tempFine = new BigDecimal((shouldPayBean.getCapital() + shouldPayBean.getInterest()) * 0.0012 * dayOfFine);
				shouldPayBean.setFine(Utils.formateDouble2Double(tempFine, 2));
				fineAmt = fineAmt.add(tempFine);
			}
		}
		System.out.println("总罚息："+Utils.formateDouble2Double(fineAmt, 2));
		return Utils.formateDouble2Double(fineAmt, 2);
	}
	
	/**
	 * 其他费用
	 * tom 2016年11月9日
	 * @return
	 */
	public double getOtherFee(){
		double otherFee = 300.00;
		return otherFee;
	}
	/**获取逾期本金
	 * tom 2016年11月9日
	 * @param shouldList
	 * @param repayDate
	 * @return
	 */
	public double getOverDueCatital(List<ShouldPayBean> shouldList,Date repayDate){
		double overdueCapital = 0.0;
		for (ShouldPayBean shouldPay : shouldList) {
			Date tempDueDate = shouldPay.getDueDate();
			if(!shouldPay.isSettle() && repayDate.after(tempDueDate)){
				overdueCapital += shouldPay.getCapital();
			}
		}
		System.out.println("逾期本金："+overdueCapital);
		return overdueCapital;
	}
	
	/**获取剩余本金（提前结清剩余本金：当期以后所有还款周期本金）
	 * tom 2016年11月9日
	 * @param shouldList
	 * @param repayDate
	 * @return
	 */
	public double getRemainCatital(List<ShouldPayBean> shouldList,Date repayDate){
		double remainCaptical = 0.0;
		for (ShouldPayBean shouldPay : shouldList) {
			if(!shouldPay.isSettle())
				remainCaptical += shouldPay.getCapital();
		}
		System.out.println("剩余本金："+remainCaptical);
		return remainCaptical;
	}
	/**
	 * 计算全部提前结清金额
	 * tom 2016年11月9日
	 */
	public void advanceAllSquared(){
		double allSquared = 0.0;
		int dayCount = 360;
		String valueDateStr = "2015-05-02";//起息日/放款完成日
		Date valueDate = Utils.formateString2Date(valueDateStr, "yyyy-MM-dd");//起息日
		double yearRate = 0.0325;//年利率
		double monthRate = yearRate/12;//月利率
		double darRate = yearRate/dayCount;//日利率
		double fianceAmt = 10000;//融资总金额
		double interestAmt = 10000;//计息总本金
		int period = 3;//融资总期数
		Date repayDate = Utils.formateString2Date("2015-07-02", "yyyy-MM-dd");//归还日期
		LoanCalculateMain lcm = new LoanCalculateMain();
		RepaySchedulePo rsp = lcm.generateCpmSchedule(fianceAmt, monthRate, period, valueDate);
		System.out.println("JSONObject.toJSONString(rsp):"+JSONObject.toJSONString(rsp));
		List<RepayScheduleDetailPo> scheduleList = rsp.getRepayScheduleDetails();
		List<ShouldPayBean> shouldList = new ArrayList<ShouldPayBean>();
		Utils.copyProperties(scheduleList, shouldList);
		double penaltyRate = 0.05;//违约金率
		for (int i = 0; i < scheduleList.size(); i++) {
			RepayScheduleDetailPo tempRsdp = scheduleList.get(i);
			ShouldPayBean tmpSpb = new ShouldPayBean();
			if(i < 1){
//				tmpSpb.setSettle(true);
			}
			BeanUtils.copyProperties(tempRsdp, tmpSpb);
			shouldList.add(tmpSpb);
		}
//		5、剩余本金
		double remainCapital = this.getRemainCatital(shouldList, repayDate);
		double overdueCapital = this.getOverDueCatital(shouldList, repayDate);
//		1、计算违约金
		double penalty = Utils.formateDouble2Double(new BigDecimal((remainCapital - overdueCapital) * penaltyRate), 2);
		System.out.println("违约金："+Utils.formateDouble2Double(penalty, 2));
//		2、应还未还其他费用
		double otherFee = this.getOtherFee();
//		3、应还未还罚息
		double fineAmt = this.getFineAmt(shouldList, repayDate);
		System.out.println("JSONObject.toJSONString(shouldList):"+JSONObject.toJSONString(shouldList));
//		4、应还未还利息本金（含当期）；
		double shouldInterest = this.getShouldInterest(shouldList, repayDate);
		allSquared = penalty + otherFee + fineAmt + shouldInterest + remainCapital;
		System.out.println("提前结清金额（全额）："+allSquared);
	}
	
	/**
	 * 给定日期，生成正常还款待还款数据
	 * tom 2016年11月8日
	 */
	public void generateCurrRepayDetail(){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String repayDateStr = "2015-08-07";//归还日期
		Date repayDate = null;//归还日期
		double repayAmt = 20000;//归还金额
		double repayRemain = repayAmt;//归还过后剩余
		Date lastDueDate = null;//最后一期还款日
		Calendar repayCl = Calendar.getInstance();
		try {
			repayCl.setTime(formater.parse(repayDateStr));
			repayDate = repayCl.getTime();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar clNow = Calendar.getInstance();
		int dayCount = 360;
		String valueDateStr = "2015-05-02";//起息日/放款完成日
		double yearRate = 0.0325;//年利率
		double monthRate = yearRate/12;//月利率
		double darRate = yearRate/dayCount;//日利率
		double fianceAmt = 400000;//融资总金额
		double interestAmt = 10000;//计息总本金
		int period = 3;//融资总期数
		
		Date valueDate = null;
		BigDecimal currRepayAmt = new BigDecimal(0);//当前应还总额
		try {
			valueDate = formater.parse(valueDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LoanCalculateMain lcm = new LoanCalculateMain();
		RepaySchedulePo rsp = lcm.generateCpmSchedule(fianceAmt, monthRate, period, valueDate);
		List<RepayScheduleDetailPo> scheduleList = rsp.getRepayScheduleDetails();//还款计划
		List<ShouldPayBean> shouldList = new ArrayList<ShouldPayBean>();
		lastDueDate = scheduleList.get(scheduleList.size() - 1).getDueDate();//最后一期还款日
		Utils.copyProperties(scheduleList, shouldList);
		for (int i = 0; i < scheduleList.size(); i++) {
			RepayScheduleDetailPo tempRsdp = scheduleList.get(i);
			ShouldPayBean tmpSpb = new ShouldPayBean();
			if(i < 1){
				tmpSpb.setSettle(true);
			}
			BeanUtils.copyProperties(tempRsdp, tmpSpb);
			shouldList.add(tmpSpb);
		}

		if(repayDate.after(lastDueDate)){
			System.out.println("超期还款");
			for (ShouldPayBean shouldPayBean : shouldList) {
				if(!shouldPayBean.isSettle()){
					//罚息计息天数
					long dayOfFine = Utils.getTimeInterval(shouldPayBean.getDueDate(), repayDate, EIntervalMode.DAYS);
					BigDecimal tempFine = new BigDecimal((shouldPayBean.getCapital() + shouldPayBean.getInterest()) * 0.0012 * dayOfFine);
					shouldPayBean.setFine(Utils.formateDouble2Double(tempFine, 2));
				}
			}
		}else{//未超期
			for (ShouldPayBean shouldPayBean : shouldList) {
				Date currDueDate = shouldPayBean.getDueDate();
				if(repayDate.after(currDueDate) && !shouldPayBean.isSettle()){//计算罚息
					//罚息计息天数
					long dayOfFine = Utils.getTimeInterval(shouldPayBean.getDueDate(), repayDate, EIntervalMode.DAYS);
					BigDecimal tempFine = new BigDecimal((shouldPayBean.getCapital() + shouldPayBean.getInterest()) * 0.0012 * dayOfFine);
					shouldPayBean.setFine(Utils.formateDouble2Double(tempFine, 2));
				}
			}
		}
		
		for (ShouldPayBean shouldPayBean : shouldList) {
			Date tempDueDate = shouldPayBean.getDueDate();
			if((repayDate.after(tempDueDate) || repayDate.equals(tempDueDate)) && !shouldPayBean.isSettle()){
				System.out.println(Utils.formateDate2String(shouldPayBean.getDueDate(), "yyyy-MM-dd"));
				currRepayAmt = currRepayAmt.add(new BigDecimal(shouldPayBean.getCapital() + shouldPayBean.getInterest() + shouldPayBean.getFine()));
			}	
		}
		System.out.println(JSONObject.toJSONString(shouldList));
		System.out.println("今天应还总额currRepayAmt:"+Utils.formateDouble2Double(currRepayAmt, 2));
		
		//模拟还款过程
		double fineAmt = 0.0;
		for (ShouldPayBean shouldPayBean : shouldList) {
			fineAmt += shouldPayBean.getFine();
		}
		System.out.println("总罚息fineAmt："+fineAmt);
		
	}
	/**
	 * tom 2016年11月8日
	 * @param args
	 */
	public static void main(String[] args) {
		RepayMain rm = new RepayMain();
//		rm.generateCurrRepayDetail();
		rm.advanceAllSquared();
	}
}
