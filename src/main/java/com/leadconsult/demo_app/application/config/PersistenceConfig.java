package com.leadconsult.demo_app.application.config;

import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;
import com.leadconsult.demo_app.domain.port.TeacherRepositoryPort;
import com.leadconsult.demo_app.infrastructure.adapter.cache.CourseRepositoryCacheAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.cache.GroupRepositoryCacheAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.cache.StudentRepositoryCacheAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.cache.TeacherRepositoryCacheAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.jpa.JpaCourseAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.jpa.JpaGroupAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.jpa.JpaStudentAdapter;
import com.leadconsult.demo_app.infrastructure.adapter.jpa.JpaTeacherAdapter;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaCourseRepository;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaGroupRepository;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaStudentRepository;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaTeacherRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
    public StudentRepositoryPort jpaStudentAdapter(JpaStudentRepository repo) {
        return new JpaStudentAdapter(repo);
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "inmemory")
    public StudentRepositoryPort studentRepositoryCacheAdapter() {
        return new StudentRepositoryCacheAdapter();
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
    public TeacherRepositoryPort jpaTeacherAdapter(JpaTeacherRepository repo) {
        return new JpaTeacherAdapter(repo);
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "inmemory")
    public TeacherRepositoryPort teacherRepositoryCacheAdapter() {
        return new TeacherRepositoryCacheAdapter();
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
    public GroupRepositoryPort jpaGroupAdapter(JpaGroupRepository repo) {
        return new JpaGroupAdapter(repo);
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "inmemory")
    public GroupRepositoryPort groupRepositoryCacheAdapter() {
        return new GroupRepositoryCacheAdapter();
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
    public CourseRepositoryPort jpaCourseAdapter(JpaCourseRepository repo) {
        return new JpaCourseAdapter(repo);
    }

    @Bean
    @ConditionalOnProperty(name = "app.persistence.mode", havingValue = "inmemory")
    public CourseRepositoryPort courseRepositoryCacheAdapter() {
        return new CourseRepositoryCacheAdapter();
    }

}
