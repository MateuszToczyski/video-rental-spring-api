package com.videos.controller;

import com.videos.domain.request.CategoryRequest;
import com.videos.domain.response.CategoryResponse;
import com.videos.service.CategoryDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("categories")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

    private final CategoryDbService dbService;

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return dbService.getCategories();
    }

    @GetMapping("{id}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        return dbService.getCategory(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        return dbService.createCategory(request);
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return dbService.updateCategory(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        dbService.deleteCategory(id);
    }
}
