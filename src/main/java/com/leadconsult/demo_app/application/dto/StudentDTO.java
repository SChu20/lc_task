package com.leadconsult.demo_app.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.List;

//TODO openAPI
public record StudentDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 7, message = "Age must be at least 7")
        @Max(value = 100, message = "Age must be less than or equal to 100")
        int age,


        @NotNull(message = "Group ID cannot be null") Long groupId,

        List<@NotNull(message = "Course ID cannot be null") Long> courseIds) {
}
