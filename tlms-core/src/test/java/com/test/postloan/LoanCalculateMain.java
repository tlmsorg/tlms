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
import com.mysql.jdbc.Util;
import com.tlms.core.util.Utils;
	


/**贷款计算测试
 * @author tom
 */
public class LoanCalculateMain {
		
	/**
	 * 等额利息按月还本
	 * tom 2016年11月7日
	 * @param fianceAmt
	 * @param monthRate
	 * @param period
	 * @param valueDate
	 * @return
	 */
	public RepaySchedulePo avgInterest(double fianceAmt,double monthRate,int period,Date valueDate){
		RepaySchedulePo rsp = new RepaySchedulePo();
		List<RepayScheduleDetailPo> rsdpList = new ArrayList<RepayScheduleDetailPo>();
		Date firstDueDate = Utils.getFirstRepayDate(valueDate);
		Calendar firstDueCl = Calendar.getInstance();
		firstDueCl.setTime(firstDueDate);
		BigDecimal monthInterest = new BigDecimal(fianceAmt * monthRate);
		BigDecimal interestAmt = new BigDecimal(fianceAmt * monthRate * period);
		BigDecimal mortgageAmt = new BigDecimal(fianceAmt);
		BigDecimal monthCaptital = new BigDecimal(fianceAmt / period);
		BigDecimal monthRepay = monthInterest.add(monthCaptital);
		BigDecimal repayAmt = interestAmt.add(mortgageAmt);
		BigDecimal remainCapital = new BigDecimal(fianceAmt);
		for (int i = 0; i < period; i++) {
			RepayScheduleDetailPo rsdp = new RepayScheduleDetailPo();
			remainCapital = remainCapital.subtract(monthCaptital);
			if(i == 0){
				rsdp.setDueDate(Utils.formateDate(firstDueCl.getTime(), "yyyy-MM-dd"));
				rsdp.setInterest(Utils.formateDouble2Double(monthInterest, 2));
				rsdp.setCapital(Utils.formateDouble2Double(monthCaptital, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(Utils.formateDouble2Double(remainCapital, 2));
			}else{
				firstDueCl.add(Calendar.MONTH, 1);
				System.out.println(Utils.formateDate2String(firstDueCl.getTime(), "yyyy-MM-dd"));
				rsdp.setDueDate(Utils.formateDate(firstDueCl.getTime(), "yyyy-MM-dd"));
				rsdp.setInterest(Utils.formateDouble2Double(monthInterest, 2));
				rsdp.setCapital(Utils.formateDouble2Double(monthCaptital, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(Utils.formateDouble2Double(remainCapital, 2));
			}
//			System.out.println(JSONObject.toJSONString(rsdp));
			rsdpList.add(rsdp);
			/*System.out.println(rsdp.getDueDate()
					+"|第"+rsdp.getPhase()+"期"
					+"|归还本金："+rsdp.getCapital()
					+"|归还利息："+rsdp.getInterest()
					+"|月供："+rsdp.getMonthRepay()
					+"|剩余本金："+rsdp.getRemainCapital());*/
		}
		rsp.setPeriod(period);
		rsp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
		rsp.setMortgageAmt(Utils.formateDouble2Double(mortgageAmt, 2));
		rsp.setInterestAmt(Utils.formateDouble2Double(interestAmt, 2));
		rsp.setRepayAmt(Utils.formateDouble2Double(repayAmt, 2));
		rsp.setRepayScheduleDetails(rsdpList);
		return rsp;
	}
	
	/**
	 * 一次付息，按期还本
	 * tom 2016年11月7日
	 * @param fianceAmt
	 * @param monthRate
	 * @param period
	 * @param valueDate
	 * @return
	 */
	public RepaySchedulePo oneInterest(double fianceAmt,double monthRate,int period,Date valueDate){
		RepaySchedulePo rsp = new RepaySchedulePo();
		List<RepayScheduleDetailPo> rsdpList = new ArrayList<RepayScheduleDetailPo>();
		Date firstDueDate = Utils.getFirstRepayDate(valueDate);
		Calendar firstDueCl = Calendar.getInstance();
		firstDueCl.setTime(firstDueDate);
//		BigDecimal monthInterest = 0;
//		BigDecimal interestAmt = new BigDecimal(fianceAmt * monthRate * period);
//		BigDecimal principle = new BigDecimal(fianceAmt);
		BigDecimal mortgageAmt = new BigDecimal(fianceAmt);
//		BigDecimal repayAmt = interestAmt.add(mortgageAmt);
		BigDecimal repayAmt = mortgageAmt;
		BigDecimal montRepay = new BigDecimal(fianceAmt/period);
		BigDecimal remainCapital = new BigDecimal(fianceAmt);
		rsp.setPeriod(period);
		rsp.setMonthRepay(Utils.formateDouble2Double(montRepay, 2));
		rsp.setMortgageAmt(Utils.formateDouble2Double(mortgageAmt, 2));
		rsp.setInterestAmt(0);
		rsp.setRepayAmt(Utils.formateDouble2Double(repayAmt, 2));
		for (int i = 0; i < period; i++) {
			RepayScheduleDetailPo rsdp = new RepayScheduleDetailPo();
			remainCapital = remainCapital.subtract(montRepay);
			System.out.println("repayAmt:"+Utils.formateDouble2Double(repayAmt, 2));
			if(i == 0){
				rsdp.setDueDate(Utils.formateDate(firstDueCl.getTime(), "yyyy-MM-dd"));
				rsdp.setInterest(0);
				rsdp.setCapital(Utils.formateDouble2Double(montRepay, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(montRepay, 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(Utils.formateDouble2Double(remainCapital, 2));
			}else{
				firstDueCl.add(Calendar.MONTH, 1);
				rsdp.setDueDate(Utils.formateDate(firstDueCl.getTime(), "yyyy-MM-dd"));
				rsdp.setInterest(0);
				rsdp.setCapital(Utils.formateDouble2Double(montRepay, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(montRepay, 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(Utils.formateDouble2Double(remainCapital, 2));
			}
			rsdpList.add(rsdp);
			System.out.println(rsdp.getDueDate()
					+"|第"+rsdp.getPhase()+"期"
					+"|归还本金："+rsdp.getCapital()
					+"|归还利息："+rsdp.getInterest()
					+"|月供："+rsdp.getMonthRepay()
					+"|剩余本金："+rsdp.getRemainCapital());
		}
		rsp.setRepayScheduleDetails(rsdpList);
		return rsp;
	}
	
	/**
	 * 按月付息，到期还本
	 * tom 2016年11月7日
	 * @param fianceAmt
	 * @param monthRate
	 * @param period
	 * @param valueDate
	 * @return
	 */
	public RepaySchedulePo monthIntetrest(double fianceAmt,double monthRate,int period,Date valueDate){
		RepaySchedulePo rsp = new RepaySchedulePo();
		List<RepayScheduleDetailPo> rspList = new ArrayList<RepayScheduleDetailPo>();
		Date firstDueDate = Utils.getFirstRepayDate(valueDate);
		Calendar dueCalendar = Calendar.getInstance();
		dueCalendar.setTime(firstDueDate);
		BigDecimal monthIntetrest = new BigDecimal(fianceAmt * monthRate);//月利息
		BigDecimal interestAmt = new BigDecimal(0);//贷款总利息
		BigDecimal mortgageAmt = new BigDecimal(fianceAmt);//贷款总金额
		BigDecimal repayAmt = new BigDecimal(fianceAmt);
		for (int i = 0; i < period; i++) {
			RepayScheduleDetailPo rsdp = new RepayScheduleDetailPo();
			if(i == 0){
				rsdp.setPhase(i+1);
				rsdp.setDueDate(firstDueDate);
				rsdp.setCapital(0);
				rsdp.setInterest(Utils.formateDouble2Double(monthIntetrest, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(monthIntetrest, 2));
				rsdp.setRemainCapital(Utils.formateDouble2Double(repayAmt, 2));
			}else if(i == period-1){
				dueCalendar.add(Calendar.MONTH, 1);
				rsdp.setDueDate(Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd"));
				interestAmt = interestAmt.add(monthIntetrest);
				rsdp.setCapital(0);
				rsdp.setInterest(Utils.formateDouble2Double(monthIntetrest, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(repayAmt.add(monthIntetrest), 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(0);
			}else{
				dueCalendar.add(Calendar.MONTH, 1);
				rsdp.setDueDate(Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd"));
				interestAmt = interestAmt.add(monthIntetrest);
				rsdp.setCapital(0);
				rsdp.setInterest(Utils.formateDouble2Double(monthIntetrest, 2));
				rsdp.setMonthRepay(Utils.formateDouble2Double(monthIntetrest, 2));
				rsdp.setPhase(i+1);
				rsdp.setRemainCapital(Utils.formateDouble2Double(repayAmt, 2));
			}
			rspList.add(rsdp);
			System.out.println(rsdp.getDueDate()
					+"|第"+rsdp.getPhase()+"期"
					+"|归还本金："+rsdp.getCapital()
					+"|归还利息："+rsdp.getInterest()
					+"|月供："+rsdp.getMonthRepay()
					+"|剩余本金："+rsdp.getRemainCapital());
//			System.out.println("^^^^^^^^^^^^^^"+JSONObject.toJSONString(rspList));
		}
		repayAmt.add(interestAmt);
		rsp.setPeriod(period);
		rsp.setInterestAmt(Utils.formateDouble2Double(interestAmt, 2));
		rsp.setMonthRepay(Utils.formateDouble2Double(monthIntetrest, 2));
		rsp.setMortgageAmt(Utils.formateDouble2Double(mortgageAmt, 2));
		rsp.setRepayAmt(Utils.formateDouble2Double(repayAmt, 2));
		rsp.setRepayScheduleDetails(rspList);
		System.out.println("^^^^^^^^^^^^^^"+JSONObject.toJSONString(rsp));
		return rsp;
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
		Date firstDueDate = Utils.getFirstRepayDate(valueDate);
		dueCalendar.setTime(firstDueDate);
		Date dueDate = null;
		BigDecimal remainCapital = new BigDecimal(fianceAmt);
//		BigDecimal fenzi = new BigDecimal(fianceAmt).multiply(new BigDecimal(monthRate)).multiply(new BigDecimal(Math.pow(1+monthRate, period)));
		BigDecimal fenzi = new BigDecimal(fianceAmt*monthRate*Math.pow(1+monthRate, period));
		BigDecimal fenmu = new BigDecimal(Math.pow(1+monthRate, period)-1);
		System.out.println("分子："+fenzi);
		System.out.println("分母："+fenmu);
		BigDecimal monthRepay = fenzi.divide(fenmu,2);
		BigDecimal repayAmt = monthRepay.multiply(new BigDecimal(period));//总还款金额
		BigDecimal interestAmt = new BigDecimal(0);//总利息
		
		for (int i = 0; i < period; i++) {
//			Calendar clTemp = Calendar.getInstance();
			RepayScheduleDetailPo rsdp = new RepayScheduleDetailPo();
			if(i == 0){
//				dueCalendar.setTime(valueDate);
//				dueCalendar.add(Calendar.MONTH, 1);
				firstDueDate = Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
				dueDate = firstDueDate;
			}else{
//				dueCalendar.setTime(firstDueDate);
				dueCalendar.add(Calendar.MONTH, 1);
				System.out.println(Utils.formateDate2String(dueCalendar.getTime(), "yyyy-MM-dd"));
				dueDate = Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
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
			
			for (RepayScheduleDetailPo loanSchedule : repayScheduleDetail) {
				System.out.println(loanSchedule.getDueDate()
						+"|第"+loanSchedule.getPhase()+"期"
						+"|归还本金："+loanSchedule.getCapital()
						+"|归还利息："+loanSchedule.getInterest()
						+"|月供："+loanSchedule.getMonthRepay()
						+"|剩余本金："+loanSchedule.getRemainCapital());
			}
		}
		rsp.setInterestAmt(Utils.formateDouble2Double(interestAmt, 2));
		rsp.setMonthRepay(Utils.formateDouble2Double(monthRepay, 2));
		rsp.setMortgageAmt(fianceAmt);
		rsp.setPeriod(period);
		rsp.setRepayAmt(Utils.formateDouble2Double(repayAmt, 2));
		System.out.println("总利息："+interestAmt.setScale(2, RoundingMode.HALF_EVEN));
		return rsp;
	}
	
	/**
	 * 生成还款计划
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
				firstDueDate = Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
				dueDate = firstDueDate;
			}else{
				dueCalendar.setTime(firstDueDate);
				dueCalendar.add(Calendar.MONTH, i);
				dueDate = Utils.formateDate(dueCalendar.getTime(), "yyyy-MM-dd");
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
		LoanCalculateMain lc = new LoanCalculateMain();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar clNow = Calendar.getInstance();
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
//		RepaySchedulePo repaySchedulePo = lc.monthIntetrest(fianceAmt, monthRate, period,valueDate);
//		RepaySchedulePo repaySchedulePo = lc.oneInterest(fianceAmt, monthRate, period,valueDate);
//		RepaySchedulePo repaySchedulePo = lc.avgInterest(fianceAmt, monthRate, period,valueDate);
		
//		System.out.println("repaySchedulePo:"+JSONObject.toJSONString(repaySchedulePo));
//		System.out.println(Utils.getFieldList(repaySchedulePo.getClass()));
		RepayScheduleVo repayScheduleVo = new RepayScheduleVo();
//		BeanUtils.copyProperties(repaySchedulePo, repayScheduleVo);
		Utils.copyProperties(repaySchedulePo, repayScheduleVo);
		System.out.println("repayScheduleVo:"+JSONObject.toJSONString(repayScheduleVo));
//		List<LoanSchedule> repayScheduleList2 = lc.generateRepaySchedule(fianceAmt, monthRate, period,valueDate);
	}
}
