package com.videos.mapping;

import com.videos.domain.entity.Group;
import com.videos.domain.request.GroupRequest;
import com.videos.domain.response.GroupResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public GroupResponse mapToResponse(Group group) {
        return new GroupResponse(group.getId(), group.getName(), group.getDiscount());
    }

    public List<GroupResponse> mapToResponseList(List<Group> groups) {
        return groups.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Group mapToEntity(GroupRequest request) {
        return new Group(request.getName(), request.getDiscount());
    }
}
