package com.videos.repository;

import com.videos.domain.entity.Copy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    List<Copy> findAll();
}
