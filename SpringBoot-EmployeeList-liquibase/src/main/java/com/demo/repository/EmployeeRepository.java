package com.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByRole(String role);
	List<Employee> findByAgeGreaterThan(Integer Age);
	List<Employee> findByNameStartingWithIgnoreCase(String prefix);
	List<Employee> findByAgeGreaterThanAndNameStartingWithIgnoreCase(Integer age, String name);
	
	 @Query("SELECT e FROM Employee e " +
	           "WHERE (:ageParam IS NULL OR e.age > :ageParam) " +
	           "AND (:nameParam IS NULL OR e.name LIKE :nameParam% )")
	    List<Employee> findByAgeGreaterThanAndNameStartingWith(@Param("ageParam") Integer age, @Param("nameParam") String name);
	
	
	    List<Employee> findByAgeGreaterThanAndNameStartingWithIgnoreCase(Integer age, String name, Sort sort);
	    
	    Page<Employee> findByAgeGreaterThanAndNameStartingWithIgnoreCase(Integer age, String name, Pageable pageable);

		Page<Employee> findByAgeGreaterThan(Integer age, Pageable pageable);
		Page<Employee> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
		Page<Employee> findAll(Pageable pageable);
		

	
	

}
