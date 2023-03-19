package com.springboot.demo.service;

import com.springboot.demo.entity.Department;
import com.springboot.demo.error.DepartmentNotFoundException;
import com.springboot.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department newDepartmentObj) {
        return departmentRepository.save(newDepartmentObj);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> res = departmentRepository.findById(departmentId);
//        if(res.isPresent()) return res.get();
//        return null;
        if(res.isEmpty()) {
            throw new DepartmentNotFoundException("Department with ID " + departmentId + " not found!");
        }
        return res.get();
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByNameIgnoreCase(departmentName);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department departmentObj) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null) {
            return null;
        }
        if(Objects.nonNull(departmentObj.getName()))
            department.setName(departmentObj.getName());
        if(Objects.nonNull(departmentObj.getAddress()))
            department.setAddress(departmentObj.getAddress());
        if(Objects.nonNull(departmentObj.getCode()))
            department.setCode(departmentObj.getCode());
        return departmentRepository.save(department);
    }

}
