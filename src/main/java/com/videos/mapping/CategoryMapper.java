package com.videos.mapping;

import com.videos.domain.entity.Category;
import com.videos.domain.request.CategoryRequest;
import com.videos.domain.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getRentalPeriod(),
                category.getRentalFee(), category.getDailyPenalty());
    }

    public List<CategoryResponse> mapToResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Category mapToEntity(CategoryRequest request) {
        return new Category(request.getName(), request.getRentalPeriod(), request.getRentalFee(),
                request.getDailyPenalty());
    }
}