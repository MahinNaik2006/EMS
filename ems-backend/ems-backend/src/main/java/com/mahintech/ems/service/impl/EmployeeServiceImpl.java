package com.mahintech.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahintech.ems.dto.EmployeeDto;
import com.mahintech.ems.entity.Employee;
import com.mahintech.ems.exception.ResourceNotFoundException;
import com.mahintech.ems.mapper.EmployeeMapper;
import com.mahintech.ems.repository.EmployeeRepository;
import com.mahintech.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow(()-> new ResourceNotFoundException("Employee is not found with this Id" + employeeId));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedemployee) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("The Id " + employeeId + "is not found")
				);
		
		employee.setFirstName(updatedemployee.getFirstName());
		employee.setLastName(updatedemployee.getLastName());
		employee.setEmailId(updatedemployee.getEmailId());

		Employee employeeupdatedobj = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(employeeupdatedobj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("The Id " + employeeId + "is not found")
				);
		
		employeeRepository.deleteById(employeeId);
	}
	 

}
