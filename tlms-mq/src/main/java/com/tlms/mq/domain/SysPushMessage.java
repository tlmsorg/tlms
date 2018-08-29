package com.tlms.mq.domain;

import java.util.Date;

public class SysPushMessage {
    private String id;

	private String accountId;

	private String sessionId;

	private String msg;

	private String msgSendFlag;

	private String msgReturnFlag;

	private String sendChannelFlag;

	private Date createTime;

	private Date sendTime;

	private Date returnTime;

	private String tranCode;

	private String msgLevel;

	private Date clockTime;

	private Boolean isMultiple;

	private String reserve1;

	private String reserve2;

	private String reserve3;

	private String reserve4;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgSendFlag() {
		return msgSendFlag;
	}

	public void setMsgSendFlag(String msgSendFlag) {
		this.msgSendFlag = msgSendFlag;
	}

	public String getMsgReturnFlag() {
		return msgReturnFlag;
	}

	public void setMsgReturnFlag(String msgReturnFlag) {
		this.msgReturnFlag = msgReturnFlag;
	}

	public String getSendChannelFlag() {
		return sendChannelFlag;
	}

	public void setSendChannelFlag(String sendChannelFlag) {
		this.sendChannelFlag = sendChannelFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getMsgLevel() {
		return msgLevel;
	}

	public void setMsgLevel(String msgLevel) {
		this.msgLevel = msgLevel;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public Boolean getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(Boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

}