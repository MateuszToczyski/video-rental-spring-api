package com.videos.mapping;

import com.videos.domain.entity.*;
import com.videos.domain.request.CopyRequest;
import com.videos.domain.response.*;
import com.videos.exception.ResourceNotFoundException;
import com.videos.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CopyMapper {

    private final VideoRepository repository;
    private final VideoMapper videoMapper;

    public CopyResponse mapToResponse(Copy copy) {
        return new CopyResponse(copy.getId(), videoMapper.mapToResponse(copy.getVideo()), copy.isAvailable(),
                mapRentalListToSimpleResponse(copy.getRentals()));
    }

    public List<CopyResponse> mapToResponseList(List<Copy> copies) {
        return copies.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Copy mapToEntity(CopyRequest request) {
        Video video = repository.findById(request.getVideoId()).orElseThrow(ResourceNotFoundException::new);
        return new Copy(video);
    }

    private RentalSimpleResponse mapRentalToSimpleResponse(Rental rental) {
        return new RentalSimpleResponse(rental.getId(), rental.getCustomer().getId(),
                rental.getCopy().getId(), rental.getStartDate(), rental.getDueDate(), rental.getReturnDate());
    }

    private List<RentalSimpleResponse> mapRentalListToSimpleResponse(List<Rental> rentals) {
        return rentals.stream()
                .map(this::mapRentalToSimpleResponse)
                .collect(Collectors.toList());
    }
}
