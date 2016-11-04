package com.test.postloan;

import java.util.List;

/**
 * @author tom
 *
 */
public class RepayScheduleVo{
	private String mortgageAmt;//贷款总额
	private String repayAmt;//还款总额
	private String interestAmt;//支付利息总款
	private String monthRepay;//月均还款
	private String peroid;//贷款期数
	private List<RepayScheduleDetailVo> repayScheduleDetails;
	
	public List<RepayScheduleDetailVo> getRepayScheduleDetails() {
		return repayScheduleDetails;
	}
	public void setRepayScheduleDetails(List<RepayScheduleDetailVo> repayScheduleDetails) {
		this.repayScheduleDetails = repayScheduleDetails;
	}
	public String getMortgageAmt() {
		return mortgageAmt;
	}
	public void setMortgageAmt(String mortgageAmt) {
		this.mortgageAmt = mortgageAmt;
	}
	public String getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(String repayAmt) {
		this.repayAmt = repayAmt;
	}
	public String getInterestAmt() {
		return interestAmt;
	}
	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}
	public String getMonthRepay() {
		return monthRepay;
	}
	public void setMonthRepay(String monthRepay) {
		this.monthRepay = monthRepay;
	}
	public String getPeroid() {
		return peroid;
	}
	public void setPeroid(String peroid) {
		this.peroid = peroid;
	}
}
