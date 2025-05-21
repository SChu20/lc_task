package com.leadconsult.demo_app.infrastructure.repository.jpa;

import com.leadconsult.demo_app.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGroupRepository extends JpaRepository<Group, Long> {
}
