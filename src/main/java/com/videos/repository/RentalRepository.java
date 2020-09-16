package com.videos.repository;

import com.videos.domain.entity.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Override
    List<Rental> findAll();
}
