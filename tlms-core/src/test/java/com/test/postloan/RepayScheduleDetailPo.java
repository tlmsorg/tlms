package com.test.postloan;

import java.util.Date;

/**
 * @author tom
 *
 */
public class RepayScheduleDetailPo {
	private int phase;//期数
	private double monthRepay;//月还款
	private double interest;//利息
	private double capital;//本金
	private Date dueDate;//当前还款日期
	private double remainCapital;//所欠本金
//	private RepaySchedulePo repaySchedulePo;
	
	

	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
/*	public RepaySchedulePo getRepaySchedulePo() {
		return repaySchedulePo;
	}
	public void setRepaySchedulePo(RepaySchedulePo repaySchedulePo) {
		this.repaySchedulePo = repaySchedulePo;
	}*/
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public double getMonthRepay() {
		return monthRepay;
	}
	public void setMonthRepay(double monthRepay) {
		this.monthRepay = monthRepay;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}

	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public double getRemainCapital() {
		return remainCapital;
	}
	public void setRemainCapital(double remainCapital) {
		this.remainCapital = remainCapital;
	}
}
