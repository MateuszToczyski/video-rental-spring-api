package com.videos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VideoRequest {
    private Long categoryId;
    private String title;
    private Integer year;
    private String director;
}
