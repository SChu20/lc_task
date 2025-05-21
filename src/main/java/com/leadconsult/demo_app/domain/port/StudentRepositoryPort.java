package com.leadconsult.demo_app.domain.port;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Student;

import java.util.List;

public interface StudentRepositoryPort {

    void save(Student student);
    void delete(Long id);


    Student findById(Long id) throws ResourceNotFoundException;
    List<Student> findAll();
    List<Student> findByGroupId(Long groupId) throws ResourceNotFoundException;
    List<Student> findByCourseId(Long courseId) throws ResourceNotFoundException;
    List<Student> findByCourseIdAndAgeGreaterThan(Long courseId, int age) throws ResourceNotFoundException;
    List<Student> findByGroupIdAndCourseId(Long groupId, Long courseId) throws ResourceNotFoundException;
}
