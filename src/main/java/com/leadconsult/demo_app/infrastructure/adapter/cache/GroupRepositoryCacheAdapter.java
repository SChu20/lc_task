package com.leadconsult.demo_app.infrastructure.adapter.cache;

import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;

import java.util.*;


public class GroupRepositoryCacheAdapter implements GroupRepositoryPort {
    private final Map<Long, Group> db = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Group findById(Long id) throws ResourceNotFoundException {
        return Optional.ofNullable(db.get(id)).orElseThrow(() -> new ResourceNotFoundException("No group found with id " + id));
    }

    @Override
    public Group save(Group group) {
        if (group.getId() == null) {
            group.setId(idCounter++);
        }
        db.put(group.getId(), group);
        return group;
    }

    @Override
    public void deleteById(Long id) {
        db.remove(id);
    }
}