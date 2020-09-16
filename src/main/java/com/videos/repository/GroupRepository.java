package com.videos.repository;

import com.videos.domain.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();
}
