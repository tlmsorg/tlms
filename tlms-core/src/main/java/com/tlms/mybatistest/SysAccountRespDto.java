package com.tlms.mybatistest;

import java.util.Date;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName SysAccountRespDto
 * @Description 返回结果对象
 * @author 160068
 * @date 2019年9月30日 上午10:04:15
 * @version V1.0
 */
public class SysAccountRespDto {
	private String id;
	private String name;
	private Integer age;
	private Double salary;
	private Date birthday;
	private String idNo;
	
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
