package com.adechinan.sbreservationsystem.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ReservationDTO {
    private Long id;
    private Long trip;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate tripLeaveDate;
    private String tripLeaveFrom;
    private String tripTo;
    private Long user;

    public ReservationDTO(Long id, LocalDate tripLeaveDate, String tripLeaveFrom, String tripTo) {
        this.id = id;
        this.tripLeaveDate = tripLeaveDate;
        this.tripLeaveFrom = tripLeaveFrom;
        this.tripTo = tripTo;
    }

    public ReservationDTO(Long trip, Long user) {
        this.trip = trip;
        this.user = user;
    }
}
