package com.leadconsult.demo_app.infrastructure.adapter.jpa;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Teacher;
import com.leadconsult.demo_app.domain.port.TeacherRepositoryPort;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaTeacherRepository;

import java.util.List;


public class JpaTeacherAdapter implements TeacherRepositoryPort {
    private final JpaTeacherRepository repo;

    public JpaTeacherAdapter(JpaTeacherRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Teacher> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Teacher> findByGroupIdAndCourseId(Long groupId, Long courseId) {
        return repo.findAllByGroupIdAndCoursesId(groupId,courseId);
    }

    @Override
    public void save(Teacher s) {
        repo.save(s);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Teacher findById(Long id) throws ResourceNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student found with id " + id));
    }
}
