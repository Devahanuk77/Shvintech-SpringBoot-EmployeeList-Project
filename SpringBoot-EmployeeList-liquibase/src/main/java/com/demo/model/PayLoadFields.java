package com.demo.model;

public class PayLoadFields {
	
	private int age;

	private String name;
	private String sortingParameter;
	
	private String sortOrder;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortingParameter() {
		return sortingParameter;
	}
	public void setSortingParameter(String sortingParameter) {
		this.sortingParameter = sortingParameter;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public PayLoadFields(int age, String name, String sortingParameter, String sortOrder) {
		this.age = age;
		this.name = name;
		this.sortingParameter = sortingParameter;
		this.sortOrder = sortOrder;
	}
	
	

}
