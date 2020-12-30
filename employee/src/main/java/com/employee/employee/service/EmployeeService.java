package com.employee.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.employee.exception.CustomException;
import com.employee.employee.model.Employee;
import com.employee.employee.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee findById(int Id) throws CustomException {
		Optional<Employee> optional = employeeRepository.findById(Id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException("Record not found");
		}
	}

	public List<Employee> findByEmpName(String empName) throws CustomException {
		Optional<List<Employee>> optional = employeeRepository.findByEmpName(empName);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException("Record not found");
		}
	}

	public void deleteById(int Id) throws CustomException {
		employeeRepository.deleteById(Id);

	}

	public Employee updateEmployee(int Id, Employee employee) {
		Optional<Employee> optional = employeeRepository.findById(Id);
		if (optional.isPresent()) {
			Employee existingemployee = optional.get();
			existingemployee.setEmpName(employee.getEmpName());
			return employeeRepository.save(existingemployee);
		} else {
			return employeeRepository.save(employee);
		}
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> findByDepartmentNo(int departmentNo) throws CustomException {
		Optional<List<Employee>> optional = employeeRepository.findByDepartmentNo(departmentNo);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException("Record not found");
		}
	}
}
