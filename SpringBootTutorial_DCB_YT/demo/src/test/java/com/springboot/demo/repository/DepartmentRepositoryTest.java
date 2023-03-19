package com.springboot.demo.repository;

import com.springboot.demo.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Department department;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .name("Marketing & Sales")
                .code("MS")
                .address("Bangalore")
                .build();
        this.department = department;
        testEntityManager.persist(department);
    }

    // (Positive) Test for findById
    @Test
    public void whenValidIdThenReturnDepartment() {
        // given
        Long id = 1L;

        // when
        Department actualDepartment = departmentRepository.findById(id).orElse(null);

        // then
        Assertions.assertNotNull(actualDepartment);
        Assertions.assertEquals(actualDepartment.getId(), department.getId());
        Assertions.assertEquals(actualDepartment.getName(), department.getName());
        Assertions.assertEquals(actualDepartment.getAddress(), department.getAddress());
        Assertions.assertEquals(actualDepartment.getCode(), department.getCode());
    }

}
