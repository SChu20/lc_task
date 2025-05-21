package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.GroupDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO log all services
@Slf4j
@Service
public class GroupService {
    private final GroupRepositoryPort repo;
    private final Mapper mapper;

    public GroupService(GroupRepositoryPort repository, Mapper mapper) {
        this.repo = repository;
        this.mapper = mapper;
    }

    public List<GroupDTO> getAllGroups() {
        List<Group> groups = repo.findAll();
        return mapper.fromGroupList(groups);
    }

    public GroupDTO getGroup(Long id) throws ResourceNotFoundException {
        return mapper.fromGroup(repo.findById(id));
    }

    public GroupDTO saveGroup(GroupDTO groupDTO) {
        log.debug("In saveGroup");
        Group group = mapper.fromGroupDTO(groupDTO);
        Group groupSaved = repo.save(group);
        log.info("New category saved with id : {}", groupSaved.getId());
        return mapper.fromGroup(groupSaved);
    }

    public void remove(Long id) {
        repo.deleteById(id);
    }
}
