package com.department.department.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Optional<Department> findByDepartmentName(String empName);
}
