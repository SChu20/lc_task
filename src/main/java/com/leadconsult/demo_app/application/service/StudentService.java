package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    private final StudentRepositoryPort repo;
    private final GroupRepositoryPort grepo;
    private final CourseRepositoryPort crepo;
    private final Mapper mapper;

    public StudentService(StudentRepositoryPort srepo, GroupRepositoryPort grepo, CourseRepositoryPort crepo, Mapper mapper) {
        this.repo = srepo;
        this.grepo = grepo;
        this.crepo = crepo;
        this.mapper = mapper;
    }

    //TODO pages
    public List<StudentDTO> getAllStudents() {
        return mapper.fromStudentList(repo.findAll());
    }

    public void remove(Long id) {
        repo.delete(id);
    }

    public List<StudentDTO> findByCourse(Long courseId) throws ResourceNotFoundException {
        return mapper.fromStudentList(repo.findByCourseId(courseId));
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) throws ResourceNotFoundException {
        repo.save(mapper.fromStudentDTO(studentDTO));
        return studentDTO;
    }

    public StudentDTO updateStudent(Long id, StudentDTO dto) throws ResourceNotFoundException {
        Student existing = repo.findById(id);

        // Update basic fields
        existing.setName(dto.name());
        existing.setAge(dto.age());

        // Update group
        Group group = grepo.findById(dto.groupId());
        existing.setGroup(group);

        // Update courses
        existing.getCourses().forEach(c -> c.getStudents().remove(existing)); // remove from old
        existing.getCourses().clear();

        List<Course> courses = crepo.findAllById(dto.courseIds());
        if (courses.size() != dto.courseIds().size())
            throw new ResourceNotFoundException("One or more course IDs are invalid");

        existing.setCourses(courses);

        // bidirectional link
        courses.forEach(course -> course.getStudents().add(existing));

        repo.save(existing);

        return dto;
    }

    public StudentDTO getStudent(Long id)  throws ResourceNotFoundException{
        return mapper.fromStudent(repo.findById(id));
    }

    public List<StudentDTO> findByCourseAndAge(Long courseId, int age) throws ResourceNotFoundException {
        return mapper.fromStudentList(repo.findByCourseIdAndAgeGreaterThan(courseId, age));
    }

    public List<StudentDTO> findByGroup(Long groupId) throws ResourceNotFoundException {
        return mapper.fromStudentList(repo.findByGroupId(groupId));
    }
}
