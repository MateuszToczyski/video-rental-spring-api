package com.videos.repository;

import com.videos.domain.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
}
