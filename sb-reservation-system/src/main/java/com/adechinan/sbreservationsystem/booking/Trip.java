package com.adechinan.sbreservationsystem.booking;

import com.adechinan.sbreservationsystem.authentication.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate leaveDate;
    private String leaveFrom;
    private String to;
    @ManyToOne
    private User user;

   public Trip(LocalDate leaveDate, String leaveFrom, String to) {
        this.leaveDate = leaveDate;
        this.leaveFrom = leaveFrom;
        this.to = to;
    }

    public Trip(LocalDate leaveDate, String leaveFrom, String to, User user) {
        this.leaveDate = leaveDate;
        this.leaveFrom = leaveFrom;
        this.to = to;
        this.user = user;
    }
}
