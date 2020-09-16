package com.videos.omdb;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OmdbConfig {

    @Value("${omdb.url}")
    private String url;

    @Value("${omdb.key}")
    private String key;
}
