package com.springboot.demo.controller;

import com.springboot.demo.entity.Department;
import com.springboot.demo.error.DepartmentNotFoundException;
import com.springboot.demo.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    private final String apiEndpointPrefix = "/api/departments";

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        LOGGER.info("GET " + apiEndpointPrefix);
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    public Department getDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("GET " + apiEndpointPrefix + "/" + departmentId);
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/departments/name/{departmentName}")
    public Department getDepartmentByName(@PathVariable("departmentName") String departmentName) {
        LOGGER.info("GET " + apiEndpointPrefix + "/name/" + departmentName);
        return departmentService.getDepartmentByName(departmentName);
    }

    @PostMapping(value = "/departments", produces = "application/json")
    public Department addDepartment(@RequestBody Department newDepartmentObj) {
        LOGGER.info("POST " + apiEndpointPrefix);
        LOGGER.info("PAYLOAD: " + newDepartmentObj.toString());
        return departmentService.addDepartment(newDepartmentObj);
    }

    @PutMapping("/departments/{departmentId}")
    public Department updateDepartmentById(@PathVariable("departmentId") Long departmentId, @RequestBody Department departmentObj) {
        LOGGER.info("PUT " + apiEndpointPrefix + "/" + departmentId);
        LOGGER.info("PAYLOAD: " + departmentObj.toString());
        return departmentService.updateDepartmentById(departmentId, departmentObj);
    }

    @DeleteMapping("/departments/{departmentId}")
    public String deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("DELETE " + apiEndpointPrefix + "/" + departmentId);
        departmentService.deleteDepartmentById(departmentId);
        return "DEPARTMENT DELETED SUCCESSFULLY";
    }

}
