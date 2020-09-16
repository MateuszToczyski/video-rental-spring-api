package com.videos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private Long groupId;
}
