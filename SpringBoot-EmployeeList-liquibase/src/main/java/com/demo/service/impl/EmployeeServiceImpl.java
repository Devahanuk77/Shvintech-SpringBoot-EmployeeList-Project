package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.exception.EmptyInputException;
import com.demo.exception.InvalidSortOrderException;
import com.demo.exception.PageNotFoundException;
import com.demo.model.Employee;
import com.demo.model.EmployeeListResponse;
import com.demo.model.EmployeeResponse;
import com.demo.model.PayLoadFields;
import com.demo.model.Payload;
import com.demo.model.PayloadData;
import com.demo.model.SortingRequest;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		if(employee.getName().isEmpty() || employee.getName().length() ==0) {
			throw new EmptyInputException("601", "Input Fields are empty");
		}
		else {
			Employee employeeSaved = employeeRepository.save(employee);
			return employeeSaved;	
		}
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public void removeEmployee(int id) {
	employeeRepository.deleteById(id);
	System.out.println("Employee object deleted successfully!");
		
	}

	@Override
	public EmployeeResponse getEmployeeList() {
		 List<Employee> employeeList = employeeRepository.findAll();
		 Integer count = employeeList.size();
		return new EmployeeResponse(count, employeeList);
	}


	    public Page<Employee> findEmployeesWithPaginationAndSorting(int pageNumber,int pageSize,String parameter){
	        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(parameter)));
	        return  employees;
	    }

		@Override
		public EmployeeResponse getEmployeeListBasedOnDepartment(String role) {
			List<Employee> employeeListBasedOnRole = employeeRepository.findByRole(role);
			 Integer count = employeeListBasedOnRole.size();
				return new EmployeeResponse(count, employeeListBasedOnRole);
		}

		@Override
		public EmployeeResponse getEmployeesBasedOnAge(Integer age) {
			List<Employee> employeeList = employeeRepository.findByAgeGreaterThan(age);
			Integer count = employeeList.size();
			return new EmployeeResponse(count, employeeList);
		}

		@Override
		public EmployeeResponse findEmployeesByNameStartingWith(String prefix) {
			List<Employee> employeeList = employeeRepository.findByNameStartingWithIgnoreCase(prefix);
			Integer count = employeeList.size();
			return new EmployeeResponse(count, employeeList);
		
		}

		

		@Override
		public EmployeeResponse getEmployeesSortedByField(SortingRequest request) {
			 Sort.Direction direction = Sort.Direction.ASC; 

		        if ("DESC".equalsIgnoreCase(request.getSortOrder())) {
		            direction = Sort.Direction.DESC;
		        }

		        Sort sort = Sort.by(direction,request.getSortingParameter());
		        List<Employee> employees = employeeRepository.findAll(sort);
		        Integer count = employees.size();

		        return new EmployeeResponse(count, employees);
	    
			
		}

		@Override
		public EmployeeResponse filterEmployees(Integer age, String name) {
			List<Employee> employees = employeeRepository.findByAgeGreaterThanAndNameStartingWithIgnoreCase(age, name);
			 Integer count = employees.size();
			return new EmployeeResponse(count, employees);
		}

		@Override
		public EmployeeResponse filterEmployeesByAgeAndName(Payload load) {
			List<Employee> employees = employeeRepository.findByAgeGreaterThanAndNameStartingWith(load.getAge(),load.getName());
			 Integer count = employees.size();
				return new EmployeeResponse(count, employees);
		}

		@Override
		public EmployeeResponse filterEmployeeListWithAgeAndNameAndSorting(PayLoadFields fields) {
			 Sort.Direction direction = Sort.Direction.ASC; 

		        if ("DESC".equalsIgnoreCase(fields.getSortOrder())) {
		            direction = Sort.Direction.DESC;
		        }
		        Sort sort = Sort.by(direction,fields.getSortingParameter());
		        List<Employee> filteredEmployees = employeeRepository.findByAgeGreaterThanAndNameStartingWithIgnoreCase(fields.getAge(),fields.getName(), sort);
		        
		        Integer count = filteredEmployees.size();
			
		       
		        return new EmployeeResponse(count,filteredEmployees );
		}

		

//		@Override
//		public EmployeeListResponse filterEmployeeListWithAgeAndNameAndSortingAndPagination (PayloadData data) {
//			
//			if (data.getEmployee().getAge() == 0 || data.getEmployee().getName() == null || data.getSortingParameter() == null || data.getSortOrder() == null) {
//		       
//		      
//		    }
//			
//			Sort.Direction direction = Sort.Direction.ASC;
//			
//			if ("DESC".equalsIgnoreCase(data.getSortOrder())) {
//				direction = Sort.Direction.DESC;
//			}
//			
//		  else if (!"ASC".equalsIgnoreCase(data.getSortOrder())) {
//		        throw new InvalidSortOrderException("Invalid sortOrder value. sortOrder must be 'ASC' or 'DESC'.");
//		    }
//
//			Pageable pageable = PageRequest.of(data.getPageNumber(), data.getPageCount(), direction,
//					data.getSortingParameter());
//			
//			
//			 Page<Employee> employeePage = employeeRepository.findByAgeGreaterThanAndNameStartingWithIgnoreCase(data.getEmployee().getAge(),
//					data.getEmployee().getName(), pageable);
//			
//			 if (data.getPageNumber() > employeePage.getTotalPages() || data.getPageNumber()>=0) {
//			        throw new PageNotFoundException("Requested page does not exist. Please check the page number once.");
//			    }
//			 
//			 EmployeeListResponse response = new EmployeeListResponse();
//			    response.setTotalCount(employeePage.getTotalElements());
//			    response.setEmployeeList(employeePage.toList());
//			return response;
//
//		}
		@Override
		public EmployeeListResponse filterEmployeeListWithAgeAndNameAndSortingAndPagination(PayloadData data) {
		    Sort.Direction direction = Sort.Direction.ASC;

		    if (data.getSortingParameter() != null && data.getSortOrder() != null) {
		        if ("DESC".equalsIgnoreCase(data.getSortOrder())) {
		            direction = Sort.Direction.DESC;
		        } else if (!"ASC".equalsIgnoreCase(data.getSortOrder())) {
		            throw new InvalidSortOrderException("Invalid sortOrder value. sortOrder must be 'ASC' or 'DESC'.");
		        }
		    }
		    if (data.getPageNumber() < 0) {
		        throw new PageNotFoundException("Invalid page number. Page number must not be lessthan zero.");
		    }


		    Pageable pageable = PageRequest.of(data.getPageNumber(), data.getPageCount(), direction, data.getSortingParameter());
		    
		    if (data.getEmployee().getAge() != 0 || data.getEmployee().getName() != null) {
		        Page<Employee> employeePage = employeeRepository.findByAgeGreaterThanAndNameStartingWithIgnoreCase(
		            data.getEmployee().getAge(),
		            data.getEmployee().getName(),
		            pageable
		            
		        );
		        
		        

		        if (data.getPageNumber() > employeePage.getTotalPages() ) {
		            throw new PageNotFoundException("Requested page does not exist. Please check the page number once.");
		        }

		        EmployeeListResponse response = new EmployeeListResponse();
		        response.setTotalCount(employeePage.getTotalElements());
		        response.setEmployeeList(employeePage.toList());
		        return response;
		    } else {
		       
		        List<Employee> allEmployees = employeeRepository.findAll();
		        EmployeeListResponse response = new EmployeeListResponse();
		        response.setTotalCount((long) allEmployees.size());
		        response.setEmployeeList(allEmployees);
		        return response;
		    }
		}
		
		
		
//		@SuppressWarnings("unchecked")
//		public EmployeeListResponse filterEmployeeListWithAgeAndNameAndSortingAndPagination(PayloadData data) {
//			
//			Page<Employee> employeePage = (Page<Employee>) new Employee() ;
//			
//			if ( data.getPageNumber()<0) {
//	            throw new PageNotFoundException("Requested page number must not be less than zero . Please check the page number once.");
//	        }
//		    Sort.Direction direction = Sort.Direction.ASC;
//
//		    if (data.getSortingParameter() != null && data.getSortOrder() != null) {
//		        if ("DESC".equalsIgnoreCase(data.getSortOrder())) {
//		            direction = Sort.Direction.DESC;
//		        } else if (!"ASC".equalsIgnoreCase(data.getSortOrder())) {
//		            throw new InvalidSortOrderException("Invalid sortOrder value. sortOrder must be 'ASC' or 'DESC'.");
//		        }
//		    }
//
//		    Pageable pageable = PageRequest.of(data.getPageNumber(), data.getPageCount(), direction, data.getSortingParameter());
//
//		    if (data.getEmployee().getAge() != 0 || data.getEmployee().getName() != null) {
//		        
//		       
//
//		        
//		        if (data.getEmployee().getAge() != 0) {
//		            employeePage = employeeRepository.findByAgeGreaterThan(data.getEmployee().getAge(), pageable);
//		        }
//		        
//		        else if (data.getEmployee().getName() != null) {
//		            employeePage = employeeRepository.findByNameStartingWithIgnoreCase(data.getEmployee().getName(), pageable);
//		        } 
//		        else {
//		           
//		            List<Employee> allEmployees = employeeRepository.findAll();
//		            EmployeeListResponse response = new EmployeeListResponse();
//		            response.setTotalCount((long) allEmployees.size());
//		            response.setEmployeeList(allEmployees);
//		            return response;
//		        }
//
//		        if ( data.getPageNumber() > employeePage.getTotalPages()) {
//		            throw new PageNotFoundException("Requested page number is not existed . Please check the page number once.");
//		        }
//
//		        EmployeeListResponse response = new EmployeeListResponse();
//		        response.setTotalCount(employeePage.getTotalElements());
//		        response.setEmployeeList(employeePage.toList());
//		        return response;
//		    } else {
//		      
//		        List<Employee> allEmployees = employeeRepository.findAll();
//		        EmployeeListResponse response = new EmployeeListResponse();
//		        response.setTotalCount((long) allEmployees.size());
//		        response.setEmployeeList(allEmployees);
//		        return response;
//		    }
//		}



		
		
		    
}

		


		





		

