package com.videos.service;

import com.videos.domain.request.CategoryRequest;
import com.videos.domain.request.VideoRequest;
import com.videos.domain.response.CategoryResponse;
import com.videos.domain.response.VideoResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoTestSuite {

    @Autowired private VideoDbService videoDbService;
    @Autowired private CategoryDbService categoryDbService;

    private CategoryResponse category;

    @Before
    public void init() {
        category = categoryDbService.createCategory(new CategoryRequest("the category", 4,
                BigDecimal.valueOf(5), BigDecimal.valueOf(10)));
    }

    @Test
    public void testCreateCategory() {
        List<CategoryResponse> categoriesBefore = categoryDbService.getCategories();
        CategoryRequest request = new CategoryRequest("category1", 3,
                BigDecimal.valueOf(10), BigDecimal.valueOf(15));
        CategoryResponse response = categoryDbService.createCategory(request);
        CategoryResponse responseById = categoryDbService.getCategory(response.getId());
        List<CategoryResponse> categoriesAfter = categoryDbService.getCategories();
        try {
            assertEquals("category1", response.getName());
            assertEquals(response.getName(), responseById.getName());
            assertEquals(1, categoriesAfter.size() - categoriesBefore.size());
        } finally {
            categoryDbService.deleteCategory(response.getId());
        }
    }

    @Test
    public void testUpdateCategory() {
        CategoryRequest createRequest = new CategoryRequest("category1", 3,
                BigDecimal.valueOf(10), BigDecimal.valueOf(15));
        CategoryResponse createResponse = categoryDbService.createCategory(createRequest);
        CategoryRequest updateRequest = new CategoryRequest("category2", 3,
                BigDecimal.valueOf(10), BigDecimal.valueOf(15));
        CategoryResponse updateResponse = categoryDbService.updateCategory(createResponse.getId(), updateRequest);
        try {
            assertEquals("category2", updateResponse.getName());
        } finally {
            categoryDbService.deleteCategory(updateResponse.getId());
        }
    }

    @Test
    public void testCreateVideo() {
        List<VideoResponse> videosBefore = videoDbService.getVideos();
        VideoRequest request = new VideoRequest(category.getId(), "video1", 2000, "director 1");
        VideoResponse response = videoDbService.createVideo(request);
        VideoResponse responseById = videoDbService.getVideo(response.getId());
        List<VideoResponse> videosAfter = videoDbService.getVideos();
        try {
            assertEquals("video1", response.getTitle());
            assertEquals(response.getTitle(), responseById.getTitle());
            assertEquals(1, videosAfter.size() - videosBefore.size());
        } finally {
            videoDbService.deleteVideo(response.getId());
        }
    }

    @Test
    public void testUpdateVideo() {
        VideoRequest createRequest = new VideoRequest(category.getId(), "video1", 2000, "director 1");
        VideoResponse createResponse = videoDbService.createVideo(createRequest);
        VideoRequest updateRequest = new VideoRequest(category.getId(), "video2", 2001, "director 2");
        VideoResponse updateResponse = videoDbService.updateVideo(createResponse.getId(), updateRequest);
        try {
            assertEquals("video2", updateResponse.getTitle());
        } finally {
            videoDbService.deleteVideo(updateResponse.getId());
        }
    }

    @After
    public void finalize() {
        categoryDbService.deleteCategory(category.getId());
    }
}
