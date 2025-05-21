package com.leadconsult.demo_app.infrastructure.api.controller;

import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) throws ResourceNotFoundException {
        return studentService.getStudent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO saveStudent(@RequestBody @Validated StudentDTO studentDTO) throws ResourceNotFoundException {
        return studentService.saveStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody @Validated StudentDTO studentDTO) throws ResourceNotFoundException {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.remove(id);
    }

    @GetMapping("/search/c/")
    public List<StudentDTO> findByCourse(@RequestParam Long courseId) throws ResourceNotFoundException {
        return studentService.findByCourse(courseId);
    }

    @GetMapping("/search/g/")
    public List<StudentDTO> findByGroup(@RequestParam Long groupId) throws ResourceNotFoundException {
        return studentService.findByGroup(groupId);
    }

    @GetMapping("/search/")
    public List<StudentDTO> findByCourseAndAge(@RequestParam Long courseId,
                                               @RequestParam int age) throws ResourceNotFoundException {
        return studentService.findByCourseAndAge(courseId, age);
    }
}
