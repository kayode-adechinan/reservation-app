package com.adechinan.sbreservationsystem.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;


    public Reservation save(Reservation reservation){
        return this.reservationRepository.save(reservation);
    }


    public List<Reservation> findByUserId(Long userId){
        return this.reservationRepository.findByUserId(userId);
    }

    public void deleteById(Long id){
        this.reservationRepository.deleteById(id);
    }
}
