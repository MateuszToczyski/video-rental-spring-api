package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CopyResponse {
    private Long id;
    private VideoResponse video;
    private boolean available;
    private List<RentalSimpleResponse> rentals;
}
