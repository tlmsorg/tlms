package com.test.postloan.copytest;

import java.util.List;

/**
 * @author tom
 *
 */
public class TestBean3 {
	private String address;
	private String phone;
	private List<TestBean4> listAddr;
	
	
	public List<TestBean4> getListAddr() {
		return listAddr;
	}
	public void setListAddr(List<TestBean4> listAddr) {
		this.listAddr = listAddr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
