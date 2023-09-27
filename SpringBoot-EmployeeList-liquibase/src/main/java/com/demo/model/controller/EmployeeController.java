package com.demo.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.model.EmployeeListResponse;
import com.demo.model.EmployeeResponse;
import com.demo.model.PayLoadFields;
import com.demo.model.Payload;
import com.demo.model.PayloadData;
import com.demo.model.Response;
import com.demo.model.SortingRequest;
import com.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	
	@PostMapping("/save-employee-data")
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee) {
		Employee addedEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(addedEmployee, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get-employee-data/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		Employee employeeFetched = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employeeFetched, HttpStatus.OK);
	}
	
	
	@PutMapping("/update-employee-data")
	public ResponseEntity<Employee> updateEmployee(@Valid  @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/delete-employee-data/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
		employeeService.getEmployeeById(id);
		employeeService.removeEmployee(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/get-employee-list")
	public ResponseEntity< EmployeeResponse> getEmployeeList(){
		 EmployeeResponse employeeList = employeeService.getEmployeeList();
		return ResponseEntity.ok(employeeList);
				
	}
	
	@PostMapping("/get-employee-list")
	public ResponseEntity<EmployeeResponse> getEmployeeListBasedOnRole(@RequestBody String role){
		EmployeeResponse employeeListBasedOnDepartment = employeeService.getEmployeeListBasedOnDepartment(role);
		return ResponseEntity.ok(employeeListBasedOnDepartment);
		
	}
	
	@PostMapping("/employees-list-age")
    private ResponseEntity<EmployeeResponse> getEmployeesListGreaterThan(@RequestBody int age) {
		EmployeeResponse allEmployees = employeeService.getEmployeesBasedOnAge(age);
        return ResponseEntity.ok(allEmployees); 
    }
	
	@PostMapping("/employees-list-name")
    private ResponseEntity<EmployeeResponse> getEmployeesListStartsWith(@RequestBody String name) {
		EmployeeResponse employeeList = employeeService.findEmployeesByNameStartingWith(name);
        return ResponseEntity.ok(employeeList);
    }
	
	@PostMapping("/employees")
    private ResponseEntity<EmployeeResponse> getEmployeesWithSortedAndOrdered(@RequestBody SortingRequest request) {
		EmployeeResponse response =  employeeService.getEmployeesSortedByField(request);
      return ResponseEntity.ok(response);
		    }
	
	@GetMapping("/employees-list-name-age/{age}/{name}")
    private ResponseEntity<EmployeeResponse> getEmployeesListAgeAndName(@PathVariable int age ,@PathVariable String name) {
		EmployeeResponse employeeList = employeeService.filterEmployees(age, name);
        return ResponseEntity.ok(employeeList);
    }
	
	@PostMapping("/employees-list-name-Age")
    private ResponseEntity<EmployeeResponse> getEmployeesList1(@RequestBody Payload load) {
		EmployeeResponse employeeList = employeeService.filterEmployeesByAgeAndName(load);			
        return ResponseEntity.ok(employeeList);
    }
	
	
	@PostMapping("/employees-list-name-Age-sort")
	private ResponseEntity<EmployeeResponse> getEmployeesListSort(@RequestBody PayLoadFields fields) {
		EmployeeResponse employeeList=	employeeService.filterEmployeeListWithAgeAndNameAndSorting(fields);	
		return ResponseEntity.ok(employeeList);
		
	}

	@PostMapping("/employees-list-name-Age-sort-page")
	public ResponseEntity<EmployeeListResponse> getEmployeesListSort(@RequestBody PayloadData data) {
	   EmployeeListResponse employeeList = employeeService.filterEmployeeListWithAgeAndNameAndSortingAndPagination(data);
	   return ResponseEntity.ok(employeeList);	      
	       
	}

	
//	@PostMapping("/employees-list-name-Age-sort-page")
//	private HttpResponse<EmployeeListResponse>  getEmployeesListSort(@RequestBody PayloadData data) {
//	   EmployeeListResponse employeeList = employeeService.filterEmployeeListWithAgeAndNameAndSortingAndPagination(data);
//	   return new HttpResponse<>(employeeList, HttpStatus.OK);      
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    

    @PostMapping("/employees/paginationAndSort/{pageNumber}/{pageSize}/{parameter}")
    private Response<Page<Employee>> getEmployeesWithPaginationAndSort(@PathVariable int pageNumber, @PathVariable int pageSize,@PathVariable String parameter) {
        Page<Employee> employeeWithPagination = employeeService.findEmployeesWithPaginationAndSorting(pageNumber, pageSize, parameter);
        return new Response<>(employeeWithPagination.getSize(), employeeWithPagination);
    }
	

}
