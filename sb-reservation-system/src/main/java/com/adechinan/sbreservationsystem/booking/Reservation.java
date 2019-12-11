package com.adechinan.sbreservationsystem.booking;

import com.adechinan.sbreservationsystem.authentication.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class
Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Trip trip;
    @ManyToOne
    private User user;

    public Reservation(Trip trip, User user) {
        this.trip = trip;
        this.user = user;
    }
}
