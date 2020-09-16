package com.videos.domain.entity;

import com.videos.exception.BusinessRuleViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RENTAL_PERIOD")
    private Integer rentalPeriod;

    @Column(name = "RENTAL_FEE")
    private BigDecimal rentalFee;

    @Column(name = "DAILY_PENALTY")
    private BigDecimal dailyPenalty;

    @OneToMany(targetEntity = Video.class, mappedBy = "category")
    private List<Video> videos = new ArrayList<>();

    @PreRemove
    public void beforeRemoval() {
        if(videos.size() > 0) {
            throw new BusinessRuleViolationException("Cannot delete category containing videos");
        }
    }

    public Category() {
    }

    public Category(String name, Integer rentalPeriod, BigDecimal rentalFee, BigDecimal dailyPenalty) {
        this.name = name;
        this.rentalPeriod = rentalPeriod;
        this.rentalFee = rentalFee;
        this.dailyPenalty = dailyPenalty;
    }
}
