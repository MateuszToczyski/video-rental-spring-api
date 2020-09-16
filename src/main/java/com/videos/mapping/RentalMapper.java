package com.videos.mapping;

import com.videos.domain.entity.Copy;
import com.videos.domain.entity.Customer;
import com.videos.domain.entity.Rental;
import com.videos.domain.request.RentalRequest;
import com.videos.domain.response.RentalResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.repository.CopyRepository;
import com.videos.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentalMapper {

    private final CustomerRepository customerRepository;
    private final CopyRepository copyRepository;
    private final CopyMapper copyMapper;

    public RentalResponse mapToResponse(Rental rental) {
        return new RentalResponse(rental.getId(), rental.getCustomer().getId(),
                copyMapper.mapToResponse(rental.getCopy()), rental.getStartDate(), rental.getDueDate(),
                rental.getReturnDate(), rental.getBaseFee(), rental.getPenalty(), rental.isSettled());
    }

    public List<RentalResponse> mapToResponseList(List<Rental> rentals) {
        return rentals.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Rental mapToEntity(RentalRequest request) {
        Copy copy = copyRepository.findById(request.getCopyId()).orElseThrow(ResourceNotFoundException::new);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(ResourceNotFoundException::new);
        return new Rental(customer, copy, request.getStartDate(), request.getDueDate(),
                request.getReturnDate(), request.getBaseFee(), request.getPenalty(), request.isSettled());
    }
}
