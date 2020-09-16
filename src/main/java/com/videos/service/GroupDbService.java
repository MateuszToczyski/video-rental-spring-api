package com.videos.service;

import com.videos.domain.entity.Group;
import com.videos.domain.request.GroupRequest;
import com.videos.domain.response.GroupResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.mapping.GroupMapper;
import com.videos.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupDbService {

    private final GroupMapper mapper;
    private final GroupRepository repository;

    public List<GroupResponse> getGroups() {
        return mapper.mapToResponseList(repository.findAll());
    }

    public GroupResponse getGroup(Long id) {
        return mapper.mapToResponse(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public GroupResponse createGroup(GroupRequest request) {
        return mapper.mapToResponse(repository.save(mapper.mapToEntity(request)));
    }

    public GroupResponse updateGroup(Long id, GroupRequest request) {
        repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Group group = mapper.mapToEntity(request);
        group.setId(id);
        return mapper.mapToResponse(repository.save(group));
    }

    public void deleteGroup(Long id) {
        repository.deleteById(id);
    }
}
