package com.leadconsult.demo_app.infrastructure.repository.jpa;


import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long> {
    long countByType(CourseType type);
}
