package com.videos.controller;

import com.videos.domain.request.CustomerRequest;
import com.videos.domain.response.CustomerResponse;
import com.videos.domain.response.RentalResponse;
import com.videos.service.CustomerDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("customers")
@CrossOrigin("*")
@AllArgsConstructor
public class CustomerController {

    private final CustomerDbService dbService;

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return dbService.getCustomers();
    }

    @GetMapping("{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return dbService.getCustomer(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest request) {
        return dbService.createCustomer(request);
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return dbService.updateCustomer(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        dbService.deleteCustomer(id);
    }

    @GetMapping("rentals/{id}")
    public RentalResponse getRental(@PathVariable Long id) {
        return dbService.getRental(id);
    }

    @GetMapping("{customerId}/calculateRental/{copyId}")
    public RentalResponse calculateRental(@PathVariable Long customerId, @PathVariable Long copyId) {
        return dbService.calculateRental(customerId, copyId);
    }

    @PostMapping("{customerId}/rent/{copyId}")
    public CustomerResponse rentVideo(@PathVariable Long customerId, @PathVariable Long copyId) {
        return dbService.rentVideo(customerId, copyId);
    }

    @PutMapping("rentals/{rentalId}/return")
    public RentalResponse returnVideo(@PathVariable Long rentalId) {
        return dbService.returnVideo(rentalId);
    }

    @PutMapping("rentals/{rentalId}/settle")
    public RentalResponse settleRental(@PathVariable Long rentalId) {
        return dbService.settleRental(rentalId);
    }
}
