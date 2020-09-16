package com.videos.mapping.omdb;

import com.videos.domain.omdb.OmdbVideoResult;
import com.videos.domain.omdb.OmdbVideoResponse;
import org.springframework.stereotype.Component;

@Component
public class OmdbVideoMapper {

    public OmdbVideoResponse mapToResponse(OmdbVideoResult video) {
        return new OmdbVideoResponse(video.getTitle(), video.getYear(), video.getDirector());
    }
}
