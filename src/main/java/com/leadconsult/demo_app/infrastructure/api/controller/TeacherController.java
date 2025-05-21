package com.leadconsult.demo_app.infrastructure.api.controller;

import com.leadconsult.demo_app.application.dto.TeacherDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO getTeacher(@PathVariable Long id) throws ResourceNotFoundException {
        return teacherService.getTeacher(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO saveTeacher(@RequestBody @Validated TeacherDTO teacherDTO) throws ResourceNotFoundException {
        return teacherService.saveTeacher(teacherDTO);
    }

    @PutMapping("/{id}")
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody @Validated TeacherDTO studentDTO) throws ResourceNotFoundException {
        return teacherService.updateTeacher(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.remove(id);
    }

}
