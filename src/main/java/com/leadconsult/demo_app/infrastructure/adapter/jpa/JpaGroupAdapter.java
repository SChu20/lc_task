package com.leadconsult.demo_app.infrastructure.adapter.jpa;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import com.leadconsult.demo_app.infrastructure.repository.jpa.JpaGroupRepository;

import java.util.List;



public class JpaGroupAdapter implements GroupRepositoryPort {

    private final JpaGroupRepository repo;

    public JpaGroupAdapter(JpaGroupRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Group> findAll() {
        return repo.findAll();
    }

    @Override
    public Group findById(Long id) throws ResourceNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No group found with id " + id));
    }

    @Override
    public Group save(Group group) {
        return repo.save(group);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
