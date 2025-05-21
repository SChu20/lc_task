package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.ReportDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.model.Teacher;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;
import com.leadconsult.demo_app.domain.port.TeacherRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final StudentRepositoryPort srepo;
    private final TeacherRepositoryPort trepo;
    private final Mapper mapper;

    public ReportService(StudentRepositoryPort srepo, TeacherRepositoryPort trepo, Mapper mapper) {
        this.srepo = srepo;
        this.trepo = trepo;
        this.mapper = mapper;
    }

    public ReportDTO getGroupCourseReport(Long groupId, Long courseId) throws ResourceNotFoundException {
        List<Student> students = srepo.findByGroupIdAndCourseId(groupId, courseId);
        List<Teacher> teachers = trepo.findByGroupIdAndCourseId(groupId, courseId);
        return new ReportDTO(mapper.fromStudentList(students),mapper.fromTeacherList(teachers));
    }
}
