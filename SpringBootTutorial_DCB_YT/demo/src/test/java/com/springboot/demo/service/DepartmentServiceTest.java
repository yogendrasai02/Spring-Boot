package com.springboot.demo.service;

import com.springboot.demo.entity.Department;
import com.springboot.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {

    }

    // (Positive) Test for public Department getDepartmentByName(String departmentName)
    @Test
    @DisplayName("Get Department Object for Valid Department Name")
    public void whenValidDepartmentNameThenReturnValidDepartment() {
        // given
        String departmentName = "Infrastructure Support";
        Department department = Department.builder()
                                .id(1L)
                                .name("Infrastructure Support")
                                .build();

        // when
        Mockito.when(departmentRepository.findByNameIgnoreCase(departmentName))
                .thenReturn(department);
        Department actualDepartment = departmentService.getDepartmentByName(departmentName);

        // then
        Mockito.verify(departmentRepository, Mockito.times(1)).findByNameIgnoreCase(departmentName);
        Assertions.assertEquals(actualDepartment.getName(), departmentName);
    }

    // (Negative) Test for public Department getDepartmentByName(String departmentName)
    @Test
    @DisplayName("Should Return NULL for Invalid Department Name")
    public void whenInvalidDepartmentNameThenReturnNull() {
        // given
        String departmentName = "foobar";

        // when
        Mockito.when(departmentRepository.findByNameIgnoreCase(departmentName))
                .thenReturn(null);
        Department actualDepartment = departmentService.getDepartmentByName(departmentName);

        // then
        Mockito.verify(departmentRepository, Mockito.times(1)).findByNameIgnoreCase(departmentName);
        Assertions.assertNull(actualDepartment);
    }

}
