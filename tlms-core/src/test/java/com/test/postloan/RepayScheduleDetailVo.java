package com.test.postloan;

import java.util.Date;

/**
 * @author tom
 *
 */
public class RepayScheduleDetailVo {
	private String phase;//期数
	private String monthRepay;//月还款
	private String interest;//利息
	private String capital;//本金
	private String dueDate;//当前还款日期
	private String remainCapital;//所欠本金
	
	
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getMonthRepay() {
		return monthRepay;
	}
	public void setMonthRepay(String monthRepay) {
		this.monthRepay = monthRepay;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getRemainCapital() {
		return remainCapital;
	}
	public void setRemainCapital(String remainCapital) {
		this.remainCapital = remainCapital;
	}
	
	
}
