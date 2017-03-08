package com.test.doubleformate;

import java.util.List;

/**
 * @author tom
 *
 */
public class Person extends PersonParent{
	private String name;
	private double score;
	List<Person> personList;
	
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
