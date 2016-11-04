package com.test.postloan;

import java.util.List;

/**
 * 
 * @author tom
 */
public class RepaySchedulePo {
	private Double mortgageAmt;//贷款总额
	private Double repayAmt;//还款总额
	private Double interestAmt;//支付利息总款
	private Double monthRepay;//月均还款
	private Integer peroid;//贷款期数
	private List<RepayScheduleDetailPo> repayScheduleDetails;
	
	public List<RepayScheduleDetailPo> getRepayScheduleDetails() {
		return repayScheduleDetails;
	}
	public void setRepayScheduleDetails(List<RepayScheduleDetailPo> repayScheduleDetails) {
		this.repayScheduleDetails = repayScheduleDetails;
	}
	public double getMortgageAmt() {
		return mortgageAmt;
	}
	public void setMortgageAmt(double mortgageAmt) {
		this.mortgageAmt = mortgageAmt;
	}
	public double getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(double repayAmt) {
		this.repayAmt = repayAmt;
	}
	public double getInterestAmt() {
		return interestAmt;
	}
	public void setInterestAmt(double interestAmt) {
		this.interestAmt = interestAmt;
	}
	public double getMonthRepay() {
		return monthRepay;
	}
	public void setMonthRepay(double monthRepay) {
		this.monthRepay = monthRepay;
	}
	public int getPeroid() {
		return peroid;
	}
	public void setPeroid(int peroid) {
		this.peroid = peroid;
	}
	
}
