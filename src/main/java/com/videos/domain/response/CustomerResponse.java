package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String address;
    private GroupResponse group;
    private List<RentalResponse> rentals;
}
