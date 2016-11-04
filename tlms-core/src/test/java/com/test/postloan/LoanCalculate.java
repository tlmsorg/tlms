package com.test.postloan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.util.Utils;
	


/**贷款计算测试
 * @author tom
 */
public class LoanCalculate {
		
	
	
	public Date formateDate(Date date,String formateStr){
		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		Date dateRet = null;
		try {
			dateRet = formate.parse(formate.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
	/**
	 * 还款计划表（等额还款抵押贷款 constant payment mortgage）
	 * tom 2016年11月2日
	 * @param fianceAmt 融资总金额
	 * @param monthRate 月利率
	 * @param period 还款期数
	 * @param valueDate 起息日
	 */
	public RepaySchedulePo generateCpmSchedule(double fianceAmt,double monthRate,int period,Date valueDate){
		RepaySchedulePo rsp = new RepaySchedulePo();
		List<RepayScheduleDetailPo> repayScheduleDetail = new ArrayList<RepayScheduleDetailPo>();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dueCalendar = Calendar.getInstance();//到期还款日 日历
		Date firstDueDate = null;
		Date dueDate = null;
		BigDecimal remainCapital = new BigDecimal(fianceAmt);
		BigDecimal fenzi = new BigDecimal(fianceAmt).multiply(new BigDecimal(monthRate)).multiply(new BigDecimal(Math.pow(1+monthRate, period)));
		System.out.println(fenzi);
		BigDecimal fenmu = new BigDecimal(Math.pow(1+monthRate, period)-1);
		System.out.println(fenmu);
		BigDecimal monthRepay = fenzi.divide(fenmu,2);
		BigDecimal repayAmt = monthRepay.multiply(new BigDecimal(period));//总还款金额
		BigDecimal interestAmt = new BigDecimal(0);//总利息
		
		
		for (int i = 0; i < period; i++) {
//			Calendar clTemp = Calendar.getInstance();
			RepayScheduleDetailPo rsdp = new RepayScheduleDetailPo();
			if(i == 0){
				dueCalendar.setTime(valueDate);
				dueCalendar.add(Calendar.MONTH, 1);
				firstDueDate = this.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
				dueDate = firstDueDate;
			}else{
				dueCalendar.setTime(firstDueDate);
				dueCalendar.add(Calendar.MONTH, i);
				dueDate = this.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
			}
			BigDecimal currInterest = remainCapital.multiply(new BigDecimal(monthRate));//当月利息
			BigDecimal currCapital = monthRepay.subtract(currInterest);//当月本金
			remainCapital = remainCapital.subtract(currCapital);
			interestAmt = interestAmt.add(currInterest);
			rsdp.setPhase(i+1);
			rsdp.setDueDate(dueDate);
			rsdp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
			rsdp.setInterest(Utils.formateDouble2Double(currInterest, 2));
			rsdp.setCapital(Utils.formateDouble2Double(currCapital, 2));
			rsdp.setRemainCapital(Utils.formateDouble2Double(remainCapital, 2));
			repayScheduleDetail.add(rsdp);
			rsp.setRepayScheduleDetails(repayScheduleDetail);
			
			/*for (RepayScheduleDetailPo loanSchedule : repayScheduleList) {
				System.out.println(loanSchedule.getDueDate()
						+"|第"+loanSchedule.getPhase()+"期"
						+"|归还本金："+loanSchedule.getCapital()
						+"|归还利息："+loanSchedule.getInterest()
						+"|月供："+loanSchedule.getMonthRepay()
						+"|剩余本金："+loanSchedule.getRemainCapital());
			}*/
		}
		rsp.setInterestAmt(Utils.formateDouble2Double(interestAmt, 2));
		rsp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
		rsp.setMortgageAmt(fianceAmt);
		rsp.setPeroid(period);
		rsp.setRepayAmt(Utils.formateDouble2Double(repayAmt, 2));
		System.out.println("总利息："+interestAmt.setScale(2, RoundingMode.HALF_EVEN));
		return rsp;
	}
	
	/**
	 * 生成huan
	 * tom 2016年11月2日
	 * @param fianceAmt 融资总金额
	 * @param monthRate 月利率
	 * @param period 还款期数
	 * @param valueDate 起息日
	 */
	public List<LoanSchedule> generateRepaySchedule(double fianceAmt,double monthRate,int period,Date valueDate){
		List<LoanSchedule> repayScheduleList = new ArrayList<LoanSchedule>();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dueCalendar = Calendar.getInstance();//到期还款日 日历
		Date firstDueDate = null;
		Date dueDate = null;
		double remainCapital = fianceAmt;
		double monthRepay = (fianceAmt*monthRate*Math.pow(1+monthRate, period))/(Math.pow(1+monthRate, period)-1);
		double repayAmt = monthRepay*period;//总还款金额
		double interestAmt = 0;//总利息
//		monthRepay = this.formateDouble(monthRepay, 2);
		for (int i = 0; i < period; i++) {
//			Calendar clTemp = Calendar.getInstance();
			LoanSchedule loanSchedule = new LoanSchedule();
			if(i == 0){
				dueCalendar.setTime(valueDate);
				dueCalendar.add(Calendar.MONTH, 1);
				firstDueDate = this.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
				dueDate = firstDueDate;
			}else{
				dueCalendar.setTime(firstDueDate);
				dueCalendar.add(Calendar.MONTH, i);
				dueDate = this.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
			}
			double currInterest = remainCapital*monthRate;//当月利息
			double currCapital = monthRepay-currInterest;//当月本金
			remainCapital = remainCapital-currCapital;
			interestAmt += currInterest;
			System.out.println(formate.format(dueDate)
					+"|第"+(i+1)+"期"
					+"|归还本金："+Utils.formateDouble(currCapital, 2)
					+"|归还利息："+Utils.formateDouble(currInterest, 2)
					+"|月供："+Utils.formateDouble(monthRepay, 2)
					+"|剩余本金："+Utils.formateDouble(remainCapital, 2));
			loanSchedule.setPhase(i+1);
			loanSchedule.setDueDate(dueDate);
			loanSchedule.setMonthRepay(Utils.formateDouble(monthRepay, 2));
			loanSchedule.setInterest(Utils.formateDouble(currInterest, 2));
			loanSchedule.setCapital(Utils.formateDouble(currCapital, 2));
			loanSchedule.setRemainCapital(Utils.formateDouble(remainCapital, 2));
			repayScheduleList.add(loanSchedule);
		}
		System.out.println("总利息："+interestAmt);
		return repayScheduleList;
	}
	
	/**
	 * 超过还款日开始计算罚息，罚息计算时间从结账日开始计算
	 */
	
	/**
	 * tom 2016年11月2日
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		LoanCalculate lc = new LoanCalculate();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar clNow = Calendar.getInstance();
//		int dayCount = lc.getDayCountOfYear(clNow.get(Calendar.YEAR));//年总天数
		int dayCount = 360;
		String settleDateStr = "2015-01-31";//结账日
		String valueDateStr = "2015-05-2";//起息日/放款完成日
		double yearRate = 0.0325;//年利率
		double monthRate = yearRate/12;//月利率
		double darRate = yearRate/dayCount;//日利率
		double fianceAmt = 400000;//融资总金额
		double interestAmt = 10000;//计息总本金
		int period = 240;//融资总期数
		
		System.out.println("今年天数："+dayCount);
		System.out.println("放款日："+valueDateStr);
		Date valueDate = formater.parse(valueDateStr);
		RepaySchedulePo repaySchedulePo = lc.generateCpmSchedule(fianceAmt, monthRate, period,valueDate);
		System.out.println("repaySchedulePo:\n"+JSONObject.toJSONString(repaySchedulePo));
		
//		System.out.println(Utils.getFieldList(repaySchedulePo.getClass()));
		RepayScheduleVo repayScheduleVo = new RepayScheduleVo();
//		BeanUtils.copyProperties(repaySchedulePo, repayScheduleVo);
		Utils.copyProperties(repaySchedulePo, repayScheduleVo);
		System.out.println("repayScheduleVo:"+JSONObject.toJSONString(repayScheduleVo));
//		List<LoanSchedule> repayScheduleList2 = lc.generateRepaySchedule(fianceAmt, monthRate, period,valueDate);
//		System.out.println(""+Utils.formateDouble2Double(fianceAmt, 2));
	}
}
