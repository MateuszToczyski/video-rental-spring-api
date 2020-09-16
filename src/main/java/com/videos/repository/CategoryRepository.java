package com.videos.repository;

import com.videos.domain.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    List<Category> findAll();
}
