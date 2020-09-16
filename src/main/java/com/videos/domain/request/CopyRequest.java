package com.videos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CopyRequest {
    private Long videoId;
    private boolean available;
}
