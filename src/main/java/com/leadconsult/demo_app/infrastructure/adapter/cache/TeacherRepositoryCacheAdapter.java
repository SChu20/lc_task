package com.leadconsult.demo_app.infrastructure.adapter.cache;

import com.leadconsult.demo_app.domain.model.Teacher;
import com.leadconsult.demo_app.domain.port.TeacherRepositoryPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherRepositoryCacheAdapter implements TeacherRepositoryPort {
    private final Map<Long, Teacher> db = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Teacher> findByGroupIdAndCourseId(Long groupId, Long courseId) {
        return db.values().stream().filter(t -> t.getGroup().getId().equals(groupId))
                                   .filter(t -> t.getCourses().stream().anyMatch(c ->c.getId().equals(courseId)))
                .toList();
    }

    @Override
    public void save(Teacher teacher) {
        if (teacher.getId() == null) {
            teacher.setId(idCounter++);
        }
        db.put(teacher.getId(), teacher);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }

    @Override
    public Teacher findById(Long id) {
        return db.get(id);
    }
}
