package com.videos.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "rentals")
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COPY_ID")
    private Copy copy;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    @Column(name = "BASE_FEE")
    private BigDecimal baseFee;

    @Column(name = "PENALTY")
    private BigDecimal penalty;

    @Column(name = "SETTLED")
    private boolean settled;

    public Rental() {
    }

    public Rental(Customer customer, Copy copy, LocalDate dueDate, BigDecimal baseFee) {
        this.startDate=LocalDate.now();
        this.customer = customer;
        this.copy = copy;
        this.dueDate = dueDate;
        this.baseFee = baseFee;
    }

    public Rental(Customer customer, Copy copy, LocalDate startDate, LocalDate dueDate, LocalDate returnDate,
                  BigDecimal baseFee, BigDecimal penalty, boolean settled) {
        this.customer = customer;
        this.copy = copy;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.baseFee = baseFee;
        this.penalty = penalty;
        this.settled = settled;
    }
}
