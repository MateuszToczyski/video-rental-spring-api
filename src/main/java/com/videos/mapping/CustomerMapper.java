package com.videos.mapping;

import com.videos.domain.entity.Customer;
import com.videos.domain.entity.Group;
import com.videos.domain.request.CustomerRequest;
import com.videos.domain.response.CustomerResponse;
import com.videos.exception.ResourceNotFoundException;
import com.videos.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final RentalMapper rentalMapper;

    public CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getAddress(),
                groupMapper.mapToResponse(customer.getGroup()), rentalMapper.mapToResponseList(customer.getRentals()));
    }

    public List<CustomerResponse> mapToResponseList(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Customer mapToEntity(CustomerRequest request) {
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow(ResourceNotFoundException::new);
        return new Customer(request.getName(), request.getAddress(), group);
    }
}
