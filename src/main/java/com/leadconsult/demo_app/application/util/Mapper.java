package com.leadconsult.demo_app.application.util;

import com.leadconsult.demo_app.application.dto.CourseDTO;
import com.leadconsult.demo_app.application.dto.GroupDTO;
import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.dto.TeacherDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.model.Teacher;

import java.util.List;

public interface Mapper {

    Group fromGroupDTO(GroupDTO dto);
    GroupDTO fromGroup(Group group);
    List<GroupDTO> fromGroupList(List<Group> groups);

    Course fromCourseDTO(CourseDTO dto);
    CourseDTO fromCourse(Course course);
    List<CourseDTO> fromCourseList(List<Course> courses);

    Student fromStudentDTO(StudentDTO dto) throws ResourceNotFoundException;
    StudentDTO fromStudent(Student student);
    List<StudentDTO> fromStudentList(List<Student> students);

    Teacher fromTeacherDTO(TeacherDTO dto) throws ResourceNotFoundException;
    TeacherDTO fromTeacher(Teacher teacher);
    List<TeacherDTO> fromTeacherList(List<Teacher> teachers);
}
