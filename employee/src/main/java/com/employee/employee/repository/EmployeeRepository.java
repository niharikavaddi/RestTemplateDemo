package com.employee.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Optional<List<Employee>> findByEmpName(String empName);

	public Optional<List<Employee>> findByDepartmentNo(int departmentNo);
}