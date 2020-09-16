package com.videos.controller;

import com.videos.domain.omdb.OmdbVideoResponse;
import com.videos.domain.request.VideoRequest;
import com.videos.domain.response.CopyResponse;
import com.videos.domain.response.VideoResponse;
import com.videos.domain.wikipedia.WikipediaResult;
import com.videos.omdb.OmdbClient;
import com.videos.service.VideoDbService;
import com.videos.wikipedia.WikipediaClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("videos")
@CrossOrigin("*")
@AllArgsConstructor
public class VideoController {

    private final VideoDbService dbService;
    private final OmdbClient omdbClient;
    private final WikipediaClient wikipediaClient;

    @GetMapping
    public List<VideoResponse> getVideos() {
        return dbService.getVideos();
    }

    @GetMapping("{id}")
    public VideoResponse getVideo(@PathVariable Long id) {
        return dbService.getVideo(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public VideoResponse createVideo(@RequestBody VideoRequest request) {
        return dbService.createVideo(request);
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    public VideoResponse updateVideo(@PathVariable Long id, @RequestBody VideoRequest request) {
        return dbService.updateVideo(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteVideo(@PathVariable Long id) {
        dbService.deleteVideo(id);
    }

    @GetMapping("copies/{copyId}")
    public CopyResponse getCopy(@PathVariable Long copyId) {
        return dbService.getCopy(copyId);
    }

    @PostMapping("{videoId}/copies")
    public VideoResponse createCopy(@PathVariable Long videoId) {
        return dbService.createCopy(videoId);
    }

    @DeleteMapping("copies/{copyId}")
    public VideoResponse deleteCopy(@PathVariable Long copyId) {
        return dbService.deleteCopy(copyId);
    }

    @GetMapping("omdb/{title}")
    public OmdbVideoResponse getOmdbVideo(@PathVariable String title) {
        return omdbClient.findVideo(title);
    }

    @GetMapping("wikipedia/{searchTerm}")
    public WikipediaResult getWikipediaResults(@PathVariable String searchTerm) {
        return wikipediaClient.findWikipediaResults(searchTerm);
    }
}
