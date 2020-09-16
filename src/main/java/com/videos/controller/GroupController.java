package com.videos.controller;

import com.videos.domain.request.GroupRequest;
import com.videos.domain.response.GroupResponse;
import com.videos.service.GroupDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("groups")
@CrossOrigin("*")
@AllArgsConstructor
public class GroupController {

    private final GroupDbService dbService;

    @GetMapping
    public List<GroupResponse> getGroups() {
        return dbService.getGroups();
    }

    @GetMapping("{id}")
    public GroupResponse getGroup(@PathVariable Long id) {
        return dbService.getGroup(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public GroupResponse createGroup(@RequestBody GroupRequest request) {
        return dbService.createGroup(request);
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody GroupRequest request) {
        return dbService.updateGroup(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        dbService.deleteGroup(id);
    }
}
