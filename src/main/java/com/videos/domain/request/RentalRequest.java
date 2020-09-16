package com.videos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RentalRequest {
    private Long customerId;
    private Long copyId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BigDecimal baseFee;
    private BigDecimal penalty;
    private boolean settled;
}
