package com.leadconsult.demo_app.domain.port;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Teacher;

import java.util.List;

public interface TeacherRepositoryPort {

    void save(Teacher teacher);
    void delete(Long id);

    Teacher findById(Long id) throws ResourceNotFoundException;
    List<Teacher> findAll();
    List<Teacher> findByGroupIdAndCourseId(Long groupId, Long courseId);
}