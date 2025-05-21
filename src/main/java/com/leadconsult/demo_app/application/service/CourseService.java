package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.CourseDTO;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.CourseType;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    private final CourseRepositoryPort repo;
    private final Mapper mapper;

    public CourseService(CourseRepositoryPort repository, Mapper mapper) {
        this.repo = repository;
        this.mapper = mapper;
    }

    public List<CourseDTO> getAllCourses() {
        return mapper.fromCourseList(repo.findAll());
    }

    public void remove(Long id) {
        repo.delete(id);
    }

    public long countByType(CourseType type) {
        return repo.countByType(type);
    }

    public void saveCourse(CourseDTO dto) {
       repo.save(mapper.fromCourseDTO(dto));
    }
}
