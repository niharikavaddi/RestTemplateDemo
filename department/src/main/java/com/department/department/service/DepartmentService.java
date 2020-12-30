package com.department.department.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.department.department.exception.CustomException;
import com.department.department.model.Department;
import com.department.department.model.Employee;
import com.department.department.repository.DepartmentRepository;

@Service("departmentService")
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department findById(int departmentId) throws CustomException {
		Optional<Department> optional = departmentRepository.findById(departmentId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException("Record not found");
		}
	}

	public Department findByDepartmentName(String departmentName) throws CustomException {
		Optional<Department> optional = departmentRepository.findByDepartmentName(departmentName);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException("Record not found");
		}
	}

	public void deleteById(int departmentId) throws CustomException {
		departmentRepository.deleteById(departmentId);

	}

	public Department updateDepartment(int departmentId, Department department) {
		Optional<Department> optional = departmentRepository.findById(departmentId);
		if (optional.isPresent()) {
			Department existingDepartment = optional.get();
			existingDepartment.setDepartmentName(department.getDepartmentName());
			existingDepartment.setTotalStudents(department.getTotalStudents());
			return departmentRepository.save(existingDepartment);
		} else {
			return departmentRepository.save(department);
		}
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department findEmployeesOfDepartment(int departmentId) throws CustomException {
		Optional<Department> optional = departmentRepository.findById(departmentId);
		List<Employee> result = null;
		if (optional.isPresent()) {
			String uri = "http://localhost:9090/findbydeptno/" + departmentId;
			ResponseEntity<List<Employee>> employees = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Employee>>() {
					});
			result = employees.getBody();
		} else {
			throw new CustomException("Department not found");
		}
		Department department = optional.get();
		department.setEmployees(result);
		return department;

	}

}
