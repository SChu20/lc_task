package com.leadconsult.demo_app.infrastructure.repository.jpa;

import com.leadconsult.demo_app.domain.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByGroupIdAndCoursesId(Long groupId, Long courseId);
}
