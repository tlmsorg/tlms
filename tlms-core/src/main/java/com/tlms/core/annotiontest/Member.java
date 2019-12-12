package com.tlms.core.annotiontest;

@DBTable(name="MEMBER")
public class Member extends MemberParent{
	@SQLString(name="ID",value="10",constraint=@Constraints(primaryKey=true))
	private String id;
	@SQLString(name="NAME",value="20")
	private String name;
	@SQLInteger(name="AGE")
	private Integer age;
	@SQLString(name="DESCRIPTION",value="50")
	private String desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
