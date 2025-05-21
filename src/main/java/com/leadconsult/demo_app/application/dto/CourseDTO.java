package com.leadconsult.demo_app.application.dto;

import com.leadconsult.demo_app.domain.model.CourseType;

public record CourseDTO(Long id, String name, CourseType courseType) {
}
