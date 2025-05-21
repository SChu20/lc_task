package com.leadconsult.demo_app.infrastructure.adapter.jpa;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaStudentRepository;

import java.util.List;


public class JpaStudentAdapter implements StudentRepositoryPort {
    private final JpaStudentRepository repo;

    public JpaStudentAdapter(JpaStudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Student> findByGroupId(Long groupId) throws ResourceNotFoundException {
        return repo.findByGroupId(groupId).
                orElseThrow(() -> new ResourceNotFoundException("No students found with groupid " + groupId));
    }

    @Override
    public Student findById(Long id) throws ResourceNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student found with id " + id));
    }

    @Override
    public void save(Student s) {
        repo.save(s);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) throws ResourceNotFoundException {
        return repo.findByCoursesId(courseId).
                orElseThrow(() -> new ResourceNotFoundException("No students found with courseid " + courseId));
    }

    @Override
    public List<Student> findByCourseIdAndAgeGreaterThan(Long courseId, int age) throws ResourceNotFoundException {
        return repo.findByCoursesIdAndAgeGreaterThan(courseId, age).
                orElseThrow(() -> new ResourceNotFoundException("No students found with courseid " + courseId + " and older than " + age));
    }

    @Override
    public List<Student> findByGroupIdAndCourseId(Long groupId, Long courseId) throws ResourceNotFoundException {
        return repo.findByGroupIdAndCoursesId(groupId, courseId);
    }


}
