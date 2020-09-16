package com.videos.domain.omdb;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OmdbVideoResponse {
    private final String title;
    private final String year;
    private final String director;
}
