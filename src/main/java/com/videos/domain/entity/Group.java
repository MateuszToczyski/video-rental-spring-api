package com.videos.domain.entity;

import com.videos.exception.BusinessRuleViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customer_groups")
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @OneToMany(targetEntity = Customer.class, mappedBy = "group")
    private List<Customer> customers = new ArrayList<>();

    @PreRemove
    public void beforeRemoval() {
        if(customers.size() > 0) {
            throw new BusinessRuleViolationException("Cannot delete group containing customers");
        }
    }

    public Group() {
    }

    public Group(String name, BigDecimal discount) {
        this.name = name;
        this.discount = discount;
    }
}
