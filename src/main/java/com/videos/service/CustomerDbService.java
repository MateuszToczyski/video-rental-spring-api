package com.videos.service;

import com.videos.domain.entity.Category;
import com.videos.domain.entity.Copy;
import com.videos.domain.entity.Customer;
import com.videos.domain.entity.Rental;
import com.videos.domain.request.CustomerRequest;
import com.videos.domain.response.CustomerResponse;
import com.videos.domain.response.RentalResponse;
import com.videos.exception.BusinessRuleViolationException;
import com.videos.exception.ResourceNotFoundException;
import com.videos.mapping.CustomerMapper;
import com.videos.mapping.RentalMapper;
import com.videos.repository.CopyRepository;
import com.videos.repository.CustomerRepository;
import com.videos.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerDbService {

    private final CustomerMapper customerMapper;
    private final RentalMapper rentalMapper;
    private final CustomerRepository customerRepository;
    private final CopyRepository copyRepository;
    private final RentalRepository rentalRepository;

    public List<CustomerResponse> getCustomers() {
        return customerMapper.mapToResponseList(customerRepository.findAll());
    }

    public CustomerResponse getCustomer(Long id) {
        return customerMapper.mapToResponse(customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        return customerMapper.mapToResponse(customerRepository.save(customerMapper.mapToEntity(request)));
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Customer customer = customerMapper.mapToEntity(request);
        customer.setId(id);
        return customerMapper.mapToResponse(customerRepository.save(customer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public RentalResponse getRental(Long id) {
        return rentalMapper.mapToResponse(rentalRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public CustomerResponse rentVideo(Long customerId, Long copyId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(ResourceNotFoundException::new);
        if(!copy.isAvailable()) {
            throw new BusinessRuleViolationException("Copy unavailable");
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow(ResourceNotFoundException::new);
        Rental rental = generateRentalDetails(customer, copy);
        customer.getRentals().add(rental);
        copy.setAvailable(false);
        customerRepository.save(customer);
        copyRepository.save(copy);
        return customerMapper.mapToResponse(customer);
    }

    public RentalResponse calculateRental(Long customerId, Long copyId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(ResourceNotFoundException::new);
        Customer customer = customerRepository.findById(customerId).orElseThrow(ResourceNotFoundException::new);
        return rentalMapper.mapToResponse(generateRentalDetails(customer, copy));
    }

    public RentalResponse returnVideo(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(ResourceNotFoundException::new);
        rental.setReturnDate(LocalDate.now());
        rental.getCopy().setAvailable(true);
        return rentalMapper.mapToResponse(rentalRepository.save(rental));
    }

    public RentalResponse settleRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(ResourceNotFoundException::new);
        if(rental.getReturnDate() == null) {
            throw new BusinessRuleViolationException("Cannot settle rental before returning the video");
        }
        rental.setSettled(true);
        return rentalMapper.mapToResponse(rentalRepository.save(rental));
    }

    private Rental generateRentalDetails(Customer customer, Copy copy) {
        Category category = copy.getVideo().getCategory();
        LocalDate dueDate = LocalDate.now().plusDays(category.getRentalPeriod());
        BigDecimal discount = customer.getGroup().getDiscount();
        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discount);
        BigDecimal baseFee = category.getRentalFee().multiply(discountMultiplier);
        return new Rental(customer, copy, dueDate, baseFee);
    }
}
