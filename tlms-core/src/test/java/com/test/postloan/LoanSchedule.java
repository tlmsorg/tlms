package com.test.postloan;

import java.util.Date;

/**
 * @author tom
 *
 */
public class LoanSchedule {
	private int phase;//期数
	private double monthRepay;//月还款
	private double interest;//利息
	private double Capital;//本金
	private Date dueDate;//当前还款日期
	private double remainCapital;//所欠本金
	
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
	public double getCapital() {
		return Capital;
	}
	public void setCapital(double Capital) {
		this.Capital = Capital;
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
