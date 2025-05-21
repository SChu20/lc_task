package com.leadconsult.demo_app.infrastructure.repository.jpa;


import com.leadconsult.demo_app.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long> {
    Optional<List<Student>> findByGroupId(Long groupId);
    Optional<List<Student>> findByCoursesId(Long groupId);
    Optional<List<Student>> findByCoursesIdAndAgeGreaterThan(Long courseId, int age);
    List<Student> findByGroupIdAndCoursesId(Long groupId, Long courseId);
}
