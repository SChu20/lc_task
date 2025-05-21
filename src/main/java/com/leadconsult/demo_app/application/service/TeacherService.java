package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.TeacherDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.model.Teacher;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import com.leadconsult.demo_app.domain.port.TeacherRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepositoryPort repo;
    private final GroupRepositoryPort grepo;
    private final CourseRepositoryPort crepo;
    private final Mapper mapper;

    public TeacherService(TeacherRepositoryPort repository, GroupRepositoryPort grepo, CourseRepositoryPort crepo, Mapper mapper) {
        this.repo = repository;
        this.grepo = grepo;
        this.crepo = crepo;
        this.mapper = mapper;
    }

    public List<TeacherDTO> getAllTeachers() {
        return mapper.fromTeacherList(repo.findAll());
    }


    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) throws ResourceNotFoundException {
        repo.save(mapper.fromTeacherDTO(teacherDTO));
        return teacherDTO;
    }

    public void remove(Long id) {
        repo.delete(id);
    }

    public TeacherDTO getTeacher(Long id) throws ResourceNotFoundException {

        return mapper.fromTeacher(repo.findById(id));
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO dto) throws ResourceNotFoundException {
        Teacher existing = repo.findById(id);

        // Update basic fields
        existing.setName(dto.name());
        existing.setAge(dto.age());

        // Update group
        Group group = grepo.findById(dto.groupId());
        existing.setGroup(group);

        // Update courses
        existing.getCourses().forEach(c -> c.getTeachers().remove(existing)); // remove from old
        existing.getCourses().clear();

        List<Course> courses = crepo.findAllById(dto.courseIds());
        if (courses.size() != dto.courseIds().size())
            throw new ResourceNotFoundException("One or more course IDs are invalid");

        existing.setCourses(courses);

        // bidirectional link
        courses.forEach(course -> course.getTeachers().add(existing));

        repo.save(existing);
        return dto;
    }
}
