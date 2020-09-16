package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RentalSimpleResponse {
    private Long id;
    private Long customerId;
    private Long copyId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
