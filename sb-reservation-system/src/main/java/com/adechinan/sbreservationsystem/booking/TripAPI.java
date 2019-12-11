package com.adechinan.sbreservationsystem.booking;

import com.adechinan.sbreservationsystem.authentication.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@CrossOrigin
public class TripAPI {
    private final TripService tripService;
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Trip save(@RequestBody TripDTO tripDTO){

       return this.userService.findById(tripDTO.getUser())
                .map(user -> this.tripService.save(new Trip(tripDTO.getLeaveDate(),
                        tripDTO.getLeaveFrom(), tripDTO.getTo(), user))).
                       orElseThrow(()-> new RuntimeException("user not found"));


    }

    @GetMapping
    public List<Trip> findAll(@RequestParam String leaveDate, @RequestParam String leaveFrom, @RequestParam String to) {
        return this.tripService.findByLeaveDateAndLeaveFromAndTo( LocalDate.parse(leaveDate), leaveFrom, to);
    }
}
