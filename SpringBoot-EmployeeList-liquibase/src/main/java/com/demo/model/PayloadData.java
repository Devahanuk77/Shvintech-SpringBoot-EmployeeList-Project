package com.demo.model;

public class PayloadData {
	
	 private Employee employee;
	
	private String sortingParameter;
	
	private String sortOrder;
	
	private int pageNumber;
	private int pageCount;
	
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public PayloadData(Employee employee, String sortingParameter, String sortOrder, int pageNumber, int pageCount) {
		this.employee = employee;
		this.sortingParameter = sortingParameter;
		this.sortOrder = sortOrder;
		this.pageNumber = pageNumber;
		this.pageCount = pageCount;
	}
	public PayloadData() {
		
	}
	
	
	
	

}
