package com.adechinan.sbreservationsystem.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;

    public Trip save(Trip trip) {
        return this.tripRepository.save(trip);
    }

    public Optional<Trip> findById(Long tripId){
        return this.tripRepository.findById(tripId);
    }

    public List<Trip> findByLeaveDateAndLeaveFromAndTo(LocalDate leaveDate, String leaveFrom, String to) {
        return this.tripRepository.findByLeaveDateAndLeaveFromAndTo(leaveDate, leaveFrom, to);
    }
}
