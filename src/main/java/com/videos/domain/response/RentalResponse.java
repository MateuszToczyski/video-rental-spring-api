package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RentalResponse {
    private Long id;
    private Long customerId;
    private CopyResponse copy;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BigDecimal baseFee;
    private BigDecimal penalty;
    private boolean settled;
}
