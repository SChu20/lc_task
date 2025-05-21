package com.leadconsult.demo_app.infrastructure.adapter.jpa;

import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.CourseType;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaCourseRepository;

import java.util.List;

public class JpaCourseAdapter implements CourseRepositoryPort {

    private final JpaCourseRepository repo;

    public JpaCourseAdapter(JpaCourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Course> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Course> findAllById(List<Long> longs) {
        return repo.findAllById(longs);
    }

    @Override
    public Course findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        repo.save(course);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public long countByType(CourseType type) {
        return repo.countByType(type);
    }
}
