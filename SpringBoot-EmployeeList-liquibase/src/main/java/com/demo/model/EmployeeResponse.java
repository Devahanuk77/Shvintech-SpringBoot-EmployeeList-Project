package com.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class EmployeeResponse  {
	
	private Integer count;
	
	private List<Employee> employees;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeResponse() {
	}

	public EmployeeResponse(Integer count, List<Employee> employees) {
		this.count = count;
		this.employees = employees;
	}

	
	

	
	
	
	
	
	
	

}
