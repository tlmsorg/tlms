package com.test.postloan;

import java.util.Date;

/**
 * @author tom
 *
 */
public class ShouldPayBean {
	private int phase;//期数
	private double monthRepay;//月还款
	private double interest;//利息
	private double capital;//本金
	private Date dueDate;//当前还款日期
	private double fine;//罚息
	private boolean isSettle;//是否结清

	
	public boolean isSettle() {
		return isSettle;
	}
	public void setSettle(boolean isSettle) {
		this.isSettle = isSettle;
	}
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
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}

	
	
}
