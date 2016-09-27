package com.tlms.core.domain;

import java.util.Date;

public class Test1OperHis {
    private String id;

	private String appCode;

	private String userCode;

	private String className;

	private String tableName;

	private String updMode;

	private String valuePre;

	private String valueNow;

	private Date operTime;

	private String fieldName;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUpdMode() {
		return updMode;
	}

	public void setUpdMode(String updMode) {
		this.updMode = updMode;
	}

	public String getValuePre() {
		return valuePre;
	}

	public void setValuePre(String valuePre) {
		this.valuePre = valuePre;
	}

	public String getValueNow() {
		return valueNow;
	}

	public void setValueNow(String valueNow) {
		this.valueNow = valueNow;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
}