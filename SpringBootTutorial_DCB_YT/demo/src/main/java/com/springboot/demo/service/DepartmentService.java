package com.springboot.demo.service;

import com.springboot.demo.entity.Department;
import com.springboot.demo.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartments();

    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public Department getDepartmentByName(String departmentName);

    public Department addDepartment(Department newDepartmentObj);

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartmentById(Long departmentId, Department departmentObj);
}
