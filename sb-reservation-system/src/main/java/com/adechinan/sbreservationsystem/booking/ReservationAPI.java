package com.adechinan.sbreservationsystem.booking;

import com.adechinan.sbreservationsystem.authentication.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationAPI {

    private final ReservationService reservationService;
    private final TripService tripService;
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Reservation save(@RequestBody ReservationDTO reservationDTO){

        return this.reservationService.save(new Reservation(
                this.tripService.findById(reservationDTO.getTrip()).orElseThrow(()-> new RuntimeException("trip not found")),
                this.userService.findById(reservationDTO.getTrip()).orElseThrow(() -> new RuntimeException("user not found"))
        ));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<ReservationDTO> findByUserId(@RequestParam Long userId){

       return this.reservationService.findByUserId(userId).stream().map(
                r-> new ReservationDTO(r.getId(), r.getTrip().getLeaveDate(),
                                                 r.getTrip().getLeaveFrom(),
                                                 r.getTrip().getTo())
        ).collect(Collectors.toList());

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{reservationId}")
    public void deleteById(@PathVariable Long reservationId){
        this.reservationService.deleteById(reservationId);
    }

}
