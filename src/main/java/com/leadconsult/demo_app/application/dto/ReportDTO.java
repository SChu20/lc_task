package com.leadconsult.demo_app.application.dto;

import java.util.List;

public record ReportDTO(List<StudentDTO> students, List<TeacherDTO> teachers) {
}
