package com.videos.repository;

import com.videos.domain.entity.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {
    @Override
    List<Video> findAll();
}
