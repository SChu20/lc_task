package com.leadconsult.demo_app.infrastructure.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper mapper;

    private StudentDTO dto;

    @BeforeEach
    void setUp() {
        dto = new StudentDTO("Ivan Ivanov", 20, 1L, List.of(1L, 2L));
    }

    @Test
    void testGetAllStudents() throws Exception {
        Mockito.when(studentService.getAllStudents()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ivan Ivanov"));
    }

    @Test
    void testGetStudent() throws Exception {
        Mockito.when(studentService.getStudent(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".name").value("Ivan Ivanov"));
    }

    @Test
    void testGetStudent_NotFound() throws Exception {
        Mockito.when(studentService.getStudent(99L)).thenThrow(new ResourceNotFoundException("Student not found"));

        mockMvc.perform(get("/api/v1/students/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveStudent() throws Exception {
        Mockito.when(studentService.saveStudent(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".age").value(20));
    }

    @Test
    void testSaveStudent_InvalidInput() throws Exception {
        StudentDTO invalidDTO = new StudentDTO("", 5, null, List.of());

        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateStudent() throws Exception {
        Mockito.when(studentService.updateStudent(eq(1L), any())).thenReturn(dto);

        mockMvc.perform(put("/api/v1/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupId").value(1L));
    }

    @Test
    void testUpdateStudent_NotFound() throws Exception {
        Mockito.when(studentService.updateStudent(eq(99L), any())).thenThrow(new ResourceNotFoundException("Student not found"));

        mockMvc.perform(put("/api/v1/students/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/v1/students/1"))
                .andExpect(status().isOk());

        Mockito.verify(studentService).remove(1L);
    }

    @Test
    void testFindByCourse() throws Exception {
        Mockito.when(studentService.findByCourse(1L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/students/search/c/").param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseIds[0]").value(1L));
    }

    @Test
    void testFindByGroup() throws Exception {
        Mockito.when(studentService.findByGroup(1L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/students/search/g/").param("groupId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].groupId").value(1L));
    }

    @Test
    void testFindByCourseAndAge() throws Exception {
        Mockito.when(studentService.findByCourseAndAge(1L, 20)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/students/search/")
                        .param("courseId", "1")
                        .param("age", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(20));
    }

    @Test
    void testFindByCourseAndAge_NotFound() throws Exception {
        Mockito.when(studentService.findByCourseAndAge(1L, 99)).thenThrow(new ResourceNotFoundException("No students found"));

        mockMvc.perform(get("/api/v1/students/search/")
                        .param("courseId", "1")
                        .param("age", "99"))
                .andExpect(status().isNotFound());
    }
}
