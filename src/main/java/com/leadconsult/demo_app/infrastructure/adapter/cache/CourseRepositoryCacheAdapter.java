package com.leadconsult.demo_app.infrastructure.adapter.cache;

import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.CourseType;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;

import java.util.*;

public class CourseRepositoryCacheAdapter implements CourseRepositoryPort {

    private final Map<Long, Course> db = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Course> findAllById(List<Long> longs) {
        return longs.stream().map(db::get).filter(Objects::nonNull).toList();
    }

    @Override
    public Course findById(Long id) {
        return db.get(id);
    }

    @Override
    public void save(Course course) {
        if (course.getId() == null) {
            course.setId(idCounter++);
        }
        db.put(course.getId(), course);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }

    @Override
    public long countByType(CourseType type) {
        return db.values().stream().filter(c -> c.getType() == type).count();
    }


}
