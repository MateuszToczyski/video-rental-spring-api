package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VideoResponse {
    private Long id;
    private CategoryResponse category;
    private String title;
    private Integer year;
    private String director;
    private List<CopySimpleResponse> copies;
}
