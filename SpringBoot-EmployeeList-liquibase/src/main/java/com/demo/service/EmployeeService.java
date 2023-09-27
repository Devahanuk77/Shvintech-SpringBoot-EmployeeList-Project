package com.demo.service;

import org.springframework.data.domain.Page;

import com.demo.model.Employee;
import com.demo.model.EmployeeListResponse;
import com.demo.model.EmployeeResponse;
import com.demo.model.PayLoadFields;
import com.demo.model.Payload;
import com.demo.model.PayloadData;
import com.demo.model.SortingRequest;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(int id);

	void removeEmployee(int id);

	EmployeeResponse getEmployeeList();

	EmployeeResponse getEmployeeListBasedOnDepartment(String role);

	EmployeeResponse getEmployeesSortedByField(SortingRequest request);

	Page<Employee> findEmployeesWithPaginationAndSorting(int pageNumber, int pageSize, String parameter);

	EmployeeResponse getEmployeesBasedOnAge(Integer age);

	EmployeeResponse findEmployeesByNameStartingWith(String prefix);

	EmployeeResponse filterEmployees(Integer age, String name);

	EmployeeResponse filterEmployeesByAgeAndName(Payload load);

	EmployeeResponse filterEmployeeListWithAgeAndNameAndSorting(PayLoadFields fields);

	

	EmployeeListResponse filterEmployeeListWithAgeAndNameAndSortingAndPagination(PayloadData data);
	

}
