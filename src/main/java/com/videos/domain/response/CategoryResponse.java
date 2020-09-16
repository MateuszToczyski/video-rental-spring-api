package com.videos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private Integer rentalPeriod;
    private BigDecimal rentalFee;
    private BigDecimal dailyPenalty;
}
