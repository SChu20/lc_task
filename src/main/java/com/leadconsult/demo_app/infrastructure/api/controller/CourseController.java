package com.leadconsult.demo_app.infrastructure.api.controller;

import com.leadconsult.demo_app.application.dto.CourseDTO;
import com.leadconsult.demo_app.application.service.CourseService;
import com.leadconsult.demo_app.domain.model.CourseType;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAllCourses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody @Validated CourseDTO course) {
        courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.remove(id);
    }

    @GetMapping("/{type}")
    public long countByType(@PathVariable CourseType type) {
        return courseService.countByType(type);
    }
}

