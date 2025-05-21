package com.leadconsult.demo_app.application.util.impl;

import com.leadconsult.demo_app.application.dto.CourseDTO;
import com.leadconsult.demo_app.application.dto.GroupDTO;
import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.dto.TeacherDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.model.Teacher;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperImpl implements Mapper {

    private final GroupRepositoryPort grepo;
    private final CourseRepositoryPort crepo;

    public MapperImpl(GroupRepositoryPort grepo, CourseRepositoryPort crepo) {
        this.grepo = grepo;
        this.crepo = crepo;
    }

    @Override
    public Group fromGroupDTO(GroupDTO groupDTO) {
        return  Group.builder().id(groupDTO.id()).name(groupDTO.name()).build();
    }

    @Override
    public GroupDTO fromGroup(Group group) {
        return new GroupDTO(group.getId(), group.getName());
    }

    @Override
    public List<GroupDTO> fromGroupList(List<Group> groups) {
        return groups.stream().map(this::fromGroup).toList();
    }

    @Override
    public Course fromCourseDTO(CourseDTO dto) {
        return Course.builder().id(dto.id()).name(dto.name()).type(dto.courseType()).build();
    }

    @Override
    public CourseDTO fromCourse(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getType());
    }

    @Override
    public List<CourseDTO> fromCourseList(List<Course> courses) {
        return courses.stream().map(this::fromCourse).toList();
    }

    @Override
    public Student fromStudentDTO(StudentDTO dto) throws ResourceNotFoundException {

        Student student = new Student();
        student.setName(dto.name());
        student.setAge(dto.age());

        Group group = grepo.findById(dto.groupId());
        student.setGroup(group);

        if (!dto.courseIds().isEmpty()) {
            List<Course> courses = crepo.findAllById(dto.courseIds());
            if (courses.size() != dto.courseIds().size()) {
                throw new ResourceNotFoundException("One or more course IDs are invalid");
            }
            student.setCourses(courses);
        }

        return student;
    }

    @Override
    public StudentDTO fromStudent(Student student) {
        return new StudentDTO(student.getName(),
                              student.getAge(),
                              student.getGroup().getId(),
                              student.getCourses().stream().map(Course::getId).toList());
    }

    @Override
    public List<StudentDTO> fromStudentList(List<Student> students) {
        return students.stream().map(this::fromStudent).toList();
    }

    @Override
    public Teacher fromTeacherDTO(TeacherDTO dto) throws ResourceNotFoundException {

        Teacher teacher = new Teacher();
        teacher.setName(dto.name());
        teacher.setAge(dto.age());

        Group group = grepo.findById(dto.groupId());
        teacher.setGroup(group);

        if (!dto.courseIds().isEmpty()) {
            List<Course> courses = crepo.findAllById(dto.courseIds());
            if (courses.size() != dto.courseIds().size()) {
                throw new ResourceNotFoundException("One or more course IDs are invalid");
            }
            teacher.setCourses(courses);
        }
        return teacher;
    }

    @Override
    public TeacherDTO fromTeacher(Teacher teacher) {
        return new TeacherDTO(teacher.getName(),
                teacher.getAge(),
                teacher.getGroup().getId(),
                teacher.getCourses().stream().map(Course::getId).toList());
    }

    @Override
    public List<TeacherDTO> fromTeacherList(List<Teacher> teachers) {
        return teachers.stream().map(this::fromTeacher).toList();
    }
}
