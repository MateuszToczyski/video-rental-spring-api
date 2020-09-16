package com.videos.service;

import com.videos.domain.entity.Category;
import com.videos.domain.request.CategoryRequest;
import com.videos.domain.response.CategoryResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.mapping.CategoryMapper;
import com.videos.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryDbService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    public List<CategoryResponse> getCategories() {
        return mapper.mapToResponseList(repository.findAll());
    }

    public CategoryResponse getCategory(Long id) {
        return mapper.mapToResponse(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        return mapper.mapToResponse(repository.save(mapper.mapToEntity(request)));
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Category category = mapper.mapToEntity(request);
        category.setId(id);
        return mapper.mapToResponse(repository.save(category));
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
