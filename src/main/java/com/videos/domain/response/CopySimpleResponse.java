package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CopySimpleResponse {
    private Long id;
    private Long videoId;
    private boolean available;
}
