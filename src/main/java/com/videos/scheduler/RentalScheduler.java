package com.videos.scheduler;

import com.videos.domain.entity.Rental;
import com.videos.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
@AllArgsConstructor
public class RentalScheduler {

    private final RentalRepository rentalRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void calculatePenalties() {
        List<Rental> rentals = rentalRepository.findAll();
        for(Rental rental : rentals) {
            calculatePenalty(rental);
        }
        rentalRepository.saveAll(rentals);
    }

    private void calculatePenalty(Rental rental) {
        if(rental.isSettled()) {
            return;
        }
        double dailyPenalty = rental.getCopy().getVideo().getCategory().getDailyPenalty().doubleValue();
        long daysOverdue = Period.between(rental.getDueDate(), LocalDate.now()).getDays();
        if(rental.getReturnDate() == null && daysOverdue > 0) {
            rental.setPenalty(BigDecimal.valueOf(dailyPenalty * daysOverdue));
        }
    }
}
