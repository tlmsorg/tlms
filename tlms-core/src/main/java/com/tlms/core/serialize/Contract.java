package com.tlms.core.serialize;

import java.io.Serializable;

public class Contract implements Serializable{
	private static final long serialVersionUID = 7351056844472203750L;
	private transient String id;
	private String contractName;
	private String contractNo;
	
	public void afterDeserialize() {
		System.out.println("模拟反序列化后，执行方法：createContract");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	public void writeObject() {
		System.out.println("writeObject");
	}
	
	
	
}
