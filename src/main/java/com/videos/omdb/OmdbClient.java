package com.videos.omdb;

import com.videos.domain.omdb.OmdbVideoResult;
import com.videos.domain.omdb.OmdbVideoResponse;
import com.videos.mapping.omdb.OmdbVideoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@AllArgsConstructor
public class OmdbClient {

    private final OmdbConfig config;
    private final RestTemplate restTemplate;
    private final OmdbVideoMapper mapper;

    public OmdbVideoResponse findVideo(String searchTerm) {
        try {
            OmdbVideoResult omdbVideoResult = restTemplate.getForObject(omdbUri(searchTerm), OmdbVideoResult.class);
            assert omdbVideoResult != null;
            return mapper.mapToResponse(omdbVideoResult);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private URI omdbUri(String searchTerm) {
        return UriComponentsBuilder.fromHttpUrl(config.getUrl())
                .queryParam("t", searchTerm)
                .queryParam("apikey", config.getKey())
                .build().encode().toUri();
    }
}
