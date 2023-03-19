package com.springboot.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.entity.Department;
import com.springboot.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        department = Department.builder()
                    .id(1123123L)
                    .name("Security & Risk")
                    .code("SR")
                    .address("Hyderabad")
                    .build();
    }

    // (Positive) Test for addDepartment
    // POST /api/departments
    @Test
    public void whenValidDataThenAddDepartment() throws Exception {
        // given
        Department department1 = Department.builder()
                                .name("Security & Risk")
                                .code("SR")
                                .address("Hyderabad")
                                .build();

        // when
        Mockito.when(departmentService.addDepartment(any(Department.class)))
                .thenReturn(department);
        /*
        💥💥💥 Why is this not working and why is the above one working?
        Mockito.when(departmentService.addDepartment(department1))
                .thenReturn(department);
        👉 With this, the response has body as NULL
        */
        String apiEndpoint = "/api/departments";
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(apiEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department1))
        ).andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.jsonPath("$.name").value(department.getName()),
                MockMvcResultMatchers.jsonPath("$.address").value(department.getAddress()),
                MockMvcResultMatchers.jsonPath("$.code").value(department.getCode())
        );
    }

}
