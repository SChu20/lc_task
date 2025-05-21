package com.leadconsult.demo_app.domain.port;

import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.CourseType;

import java.util.List;

public interface CourseRepositoryPort {
    List<Course> findAll();
    List<Course> findAllById(List<Long> longs);
    Course findById(Long id);
    void save(Course course);
    void delete(Long id);
    long countByType(CourseType type);
}