package com.videos.wikipedia;

import com.videos.domain.wikipedia.WikipediaResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@AllArgsConstructor
public class WikipediaClient {

    private final WikipediaConfig config;
    private final RestTemplate restTemplate;

    public WikipediaResult findWikipediaResults(String id) {
        try {
            return restTemplate.getForObject(wikipediaUri(id), WikipediaResult.class);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private URI wikipediaUri(String searchTerm) {
        return UriComponentsBuilder.fromHttpUrl(config.getUrl())
                .queryParam("action", "query")
                .queryParam("list", "search")
                .queryParam("origin", "*")
                .queryParam("format", "json")
                .queryParam("srsearch", searchTerm)
                .build().encode().toUri();
    }
}