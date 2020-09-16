package com.videos.domain.entity;

import com.videos.exception.BusinessRuleViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @OneToMany(targetEntity = Rental.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Rental> rentals = new ArrayList<>();

    @PreRemove
    public void beforeRemoval() {
        if(rentals.size() > 0) {
            throw new BusinessRuleViolationException("Cannot delete customer with rental history");
        }
    }

    public Customer() {
    }

    public Customer(String name, String address, Group group) {
        this.name = name;
        this.address = address;
        this.group = group;
    }
}
