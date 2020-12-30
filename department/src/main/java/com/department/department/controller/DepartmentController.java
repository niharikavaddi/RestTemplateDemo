package com.department.department.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.department.department.exception.CustomException;
import com.department.department.model.Department;
import com.department.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@GetMapping(value = "getall")
	public List<Department> findAll() {
		log.info("Fetching all Departments");
		return departmentService.findAll();
	}

	@GetMapping(value = "getbyid/{departmentId}")
	public Department findById(@PathVariable("departmentId") int departmentId) {
		Department department = null;
		try {
			log.debug("Processing Data");
			department = departmentService.findById(departmentId);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return department;
	}

	@GetMapping(value = "findbydeptname/{departmentName}")
	public Department findByDepartmentName(@PathVariable("departmentName") String departmentName) {
		Department department = null;
		try {
			log.debug("Processing Data");
			department = departmentService.findByDepartmentName(departmentName);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return department;
	}

	@DeleteMapping(value = "deletebyid/{departmentId}")
	public void deleteById(@PathVariable("departmentId") int departmentId) {
		try {
			departmentService.deleteById(departmentId);
			log.info("employee deleted");
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}

	}

	@PostMapping(value = "update/{departmentId}")
	public Department updateDepartment(@PathVariable("departmentId") int departmentId,
			@RequestBody Department department) {
		log.debug("Updating Data");
		return departmentService.updateDepartment(departmentId, department);

	}

	@PostMapping(value = "create")
	public Department createDepartment(@RequestBody Department department) {
		log.info(department.getDepartmentName() + " saved");
		return departmentService.createDepartment(department);
	}

	@GetMapping(value = "findemployeesofdepartment/{departmentId}")
	public Department findEmployeesOfDepartment(@PathVariable("departmentId") int departmentId) {
		Department department = null;
		try {
			log.debug("Fetching employees by department number");
			department = departmentService.findEmployeesOfDepartment(departmentId);
		} catch (CustomException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return department;

	}
}
