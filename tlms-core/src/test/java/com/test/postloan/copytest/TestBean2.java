package com.test.postloan.copytest;

import java.util.List;

/**
 * @author tom
 *
 */
public class TestBean2 extends TestParent{
	private String name;
	private String sex;
	private String score;
	private String age;
	private List<TestBean3> tbList;
	private TestBean4 testBean4;
	

	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public TestBean4 getTestBean4() {
		return testBean4;
	}
	public void setTestBean4(TestBean4 testBean4) {
		this.testBean4 = testBean4;
	}
	public List<TestBean3> getTbList() {
		return tbList;
	}
	public void setTbList(List<TestBean3> tbList) {
		this.tbList = tbList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
	
}
