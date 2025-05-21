package com.leadconsult.demo_app.infrastructure.adapter.cache;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;

import java.util.*;

public class StudentRepositoryCacheAdapter implements StudentRepositoryPort {

    private final Map<Long, Student> db = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Student> findByGroupId(Long groupId) throws ResourceNotFoundException {
        //TODO not supported
        return List.of();
    }

    @Override
    public Student findById(Long id) throws ResourceNotFoundException {
        return Optional.ofNullable(db.get(id)).orElseThrow(() -> new ResourceNotFoundException("No student found with id " + id));
    }

    @Override
    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(idCounter++);
        }
        db.put(student.getId(), student);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return db.values().stream()
                .filter(s -> s.getGroup() != null && s.getGroup().getId().equals(courseId))
                .toList();
    }

    @Override
    public List<Student> findByCourseIdAndAgeGreaterThan(Long courseId, int age) {
        return db.values().stream()
                .filter(s -> s.getAge() > age
                        && s.getCourses().stream().anyMatch(course -> course.getId().equals(courseId)))
                .toList();
    }

    @Override
    public List<Student> findByGroupIdAndCourseId(Long groupId, Long courseId) {
        return db.values().stream().filter(s -> s.getGroup().getId().equals(groupId))
                .filter(t -> t.getCourses().stream().anyMatch(c ->c.getId().equals(courseId)))
                .toList();
    }
}
