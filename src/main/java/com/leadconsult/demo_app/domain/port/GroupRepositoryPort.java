package com.leadconsult.demo_app.domain.port;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Group;

import java.util.List;

public interface GroupRepositoryPort {
    List<Group> findAll();
    Group findById(Long id) throws ResourceNotFoundException;
    Group save(Group group);
    void deleteById(Long id);
}
