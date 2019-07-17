package com.tlms.core.annotiontest;

@DBTable(name="MEMBER")
public class Member {
	@SQLString(name="ID",value="10",constraint=@Constraints(primaryKey=true))
	private String id;
	@SQLString(name="NAME",value="20")
	private String name;
	@SQLInteger(name="AGE")
	private Integer age;
	@SQLString(name="DESCRIPTION",value="50")
	private String desc;
}
