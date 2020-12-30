package com.employee.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.employee.exception.CustomException;
import com.employee.employee.model.Employee;
import com.employee.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("getall")
	public List<Employee> findAll() {
		log.info("Fetching all Employees");
		return employeeService.findAll();
	}

	@GetMapping("getbyid/{Id}")
	public Employee findById(@PathVariable("Id") int Id) {
		Employee employee = null;
		try {
			log.debug("Processing Data");
			employee = employeeService.findById(Id);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return employee;
	}

	@GetMapping("getbyname/{empName}")
	public List<Employee> findByEmpName(@PathVariable("empName") String empName) {
		List<Employee> employees = null;
		try {
			log.debug("Processing Data");
			employees = employeeService.findByEmpName(empName);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return employees;
	}

	@PostMapping("update/{Id}")
	public Employee updateEmployee(@PathVariable("Id") int Id, @RequestBody Employee employee) {
		log.debug("Updating Data");
		return employeeService.updateEmployee(Id, employee);
	}

	@PostMapping(value = "create")
	public Employee createDepartment(@RequestBody Employee employee) {
		log.info(employee.getEmpName() + " saved");
		return employeeService.createEmployee(employee);
	}

	@DeleteMapping("delete/{Id}")
	public void deleteById(@PathVariable("Id") int Id) {
		try {
			employeeService.deleteById(Id);
			log.info("employee deleted");
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("findbydeptno/{departmentNo}")
	public List<Employee> findByDepartmentNo(@PathVariable("departmentNo") int departmentNo) {
		List<Employee> employees = null;
		try {
			log.debug("Fetching data by department number");
			employees = employeeService.findByDepartmentNo(departmentNo);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return employees;
	}
}
