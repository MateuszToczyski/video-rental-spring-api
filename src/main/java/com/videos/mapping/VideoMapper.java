package com.videos.mapping;

import com.videos.domain.entity.Category;
import com.videos.domain.entity.Copy;
import com.videos.domain.entity.Video;
import com.videos.domain.request.VideoRequest;
import com.videos.domain.response.CopySimpleResponse;
import com.videos.domain.response.VideoResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VideoMapper {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public VideoResponse mapToResponse(Video video) {
        return new VideoResponse(video.getId(), categoryMapper.mapToResponse(video.getCategory()), video.getTitle(),
                video.getYear(), video.getDirector(), mapCopyListToSimpleResponse(video.getCopies()));
    }

    public List<VideoResponse> mapToResponseList(List<Video> videos) {
        return videos.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Video mapToEntity(VideoRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(ResourceNotFoundException::new);
        return new Video(category, request.getTitle(), request.getYear(), request.getDirector());
    }

    private CopySimpleResponse mapCopyToSimpleResponse(Copy copy) {
        return new CopySimpleResponse(copy.getId(), copy.getVideo().getId(), copy.isAvailable());
    }

    private List<CopySimpleResponse> mapCopyListToSimpleResponse(List<Copy> copies) {
        return copies.stream()
                .map(this::mapCopyToSimpleResponse)
                .collect(Collectors.toList());
    }
}
