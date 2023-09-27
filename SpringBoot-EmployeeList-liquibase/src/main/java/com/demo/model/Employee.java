package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "employee_data")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@NotEmpty(message = "Employee name is mandatory shouldn't be null or empty")
	private String name;
	
	@NotEmpty(message = "Employee role shouldn't be null or empty")
	private String role;
	
	@Min(18)
	private Integer age;
	
	@DecimalMin("1000.0")
	@DecimalMax("1000000.0")
	private double salary;
	
	@Email(message = "Invalid email address")
	private String email;
	
	@NotEmpty(message = "Password shouldn't be null or empty")
	private String password;
	
	@NotEmpty(message = "Address shouldn't be null or empty")
	private String address;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", age=" + age + ", salary=" + salary
				+ ", email=" + email + ", password=" + password + ", address=" + address + "]";
	}
	

}
