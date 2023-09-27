package com.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class EmployeeListResponse {
	
	  private long totalCount;
	  private List<Employee> employeeList;
	  
	  
		public long getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(long totalCount) {
			this.totalCount = totalCount;
		}
		public List<Employee> getEmployeeList() {
			return employeeList;
		}
		public void setEmployeeList(List<Employee> employeeList) {
			this.employeeList = employeeList;
		}
		public EmployeeListResponse(long totalCount, List<Employee> employeeList) {
			this.totalCount = totalCount;
			this.employeeList = employeeList;
		}
		public EmployeeListResponse() {
		
		}
		
		
	    
	
	
	
	
	

}
