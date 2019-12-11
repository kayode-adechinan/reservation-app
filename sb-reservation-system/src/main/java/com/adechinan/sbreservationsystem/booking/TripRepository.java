package com.adechinan.sbreservationsystem.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByLeaveDateAndLeaveFromAndTo(LocalDate leaveDate, String leaveFrom, String to);
}
