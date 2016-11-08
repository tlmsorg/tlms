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

	/**
	 * 给定日期，生成正常还款待还款数据
	 * tom 2016年11月8日
	 */
	public void generateCurrRepayDetail(){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String repayDateStr = "2015-08-05";
		Date repayDate = null;//归还日期
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
		
		
//		List listTest = new ArrayList();
//		RepayMain rm = new RepayMain();
//		System.out.println("******:"+Itest.class.isAssignableFrom(rm.getClass()));
//		System.out.println("*******************:"+List.class.isAssignableFrom(scheduleList.getClass()));
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
		}else{
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
		System.out.println("currRepayAmt:"+Utils.formateDouble2Double(currRepayAmt, 2));
	}
	/**
	 * tom 2016年11月8日
	 * @param args
	 */
	public static void main(String[] args) {
		RepayMain rm = new RepayMain();
		rm.generateCurrRepayDetail();
	}
}
