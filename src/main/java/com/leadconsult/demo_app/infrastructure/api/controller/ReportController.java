package com.leadconsult.demo_app.infrastructure.api.controller;

import com.leadconsult.demo_app.application.dto.ReportDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reports")
public class ReportController {

    private final ReportService reportsService;

    public ReportController(ReportService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/group-course")
    public ReportDTO getGroupCourseReport(@RequestParam Long groupId,
                                          @RequestParam Long courseId) throws ResourceNotFoundException {
        return reportsService.getGroupCourseReport(groupId, courseId);
    }
}
