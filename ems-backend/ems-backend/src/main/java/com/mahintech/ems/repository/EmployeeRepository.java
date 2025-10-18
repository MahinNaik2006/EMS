package com.mahintech.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahintech.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
