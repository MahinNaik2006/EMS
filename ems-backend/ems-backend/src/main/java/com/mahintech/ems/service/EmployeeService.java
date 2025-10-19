package com.mahintech.ems.service;

import java.util.List;

import com.mahintech.ems.dto.EmployeeDto;

public interface EmployeeService{ 
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long employeeId);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee(Long employeeId , EmployeeDto updatedemployee);
	
	void deleteEmployee(Long employeeId);

}
