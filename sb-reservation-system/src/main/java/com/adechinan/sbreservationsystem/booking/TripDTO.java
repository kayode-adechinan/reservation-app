package com.adechinan.sbreservationsystem.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TripDTO {

    private LocalDate leaveDate;
    private String leaveFrom;
    private String to;
    private Long user;

    public TripDTO(LocalDate leaveDate, String leaveFrom, String to, Long user) {
        this.leaveDate = leaveDate;
        this.leaveFrom = leaveFrom;
        this.to = to;
        this.user = user;
    }
}
