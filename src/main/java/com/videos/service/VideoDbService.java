package com.videos.service;

import com.videos.domain.entity.Copy;
import com.videos.domain.entity.Video;
import com.videos.domain.request.VideoRequest;
import com.videos.domain.response.CopyResponse;
import com.videos.domain.response.VideoResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.mapping.CopyMapper;
import com.videos.mapping.VideoMapper;
import com.videos.repository.CopyRepository;
import com.videos.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoDbService {

    private final VideoMapper videoMapper;
    private final CopyMapper copyMapper;
    private final VideoRepository videoRepository;
    private final CopyRepository copyRepository;

    public List<VideoResponse> getVideos() {
        return videoMapper.mapToResponseList(videoRepository.findAll());
    }

    public VideoResponse getVideo(Long id) {
        return videoMapper.mapToResponse(videoRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public VideoResponse createVideo(VideoRequest request) {
        return videoMapper.mapToResponse(videoRepository.save(videoMapper.mapToEntity(request)));
    }

    public VideoResponse updateVideo(Long id, VideoRequest request) {
        videoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Video video = videoMapper.mapToEntity(request);
        video.setId(id);
        return videoMapper.mapToResponse(videoRepository.save(video));
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    public CopyResponse getCopy(Long id) {
        return copyMapper.mapToResponse(copyRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public VideoResponse createCopy(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(ResourceNotFoundException::new);
        Copy copy = new Copy(video);
        video.getCopies().add(copy);
        return videoMapper.mapToResponse(videoRepository.save(video));
    }

    public VideoResponse deleteCopy(Long id) {
        Copy copy = copyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Video video = copy.getVideo();
        video.getCopies().remove(copy);
        copyRepository.deleteById(copy.getId());
        videoRepository.save(video);
        return videoMapper.mapToResponse(videoRepository.findById(video.getId()).orElseThrow(ResourceNotFoundException::new));
    }
}
