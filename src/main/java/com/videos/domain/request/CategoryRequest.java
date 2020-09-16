package com.videos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {
    private String name;
    private Integer rentalPeriod;
    private BigDecimal rentalFee;
    private BigDecimal dailyPenalty;
}
