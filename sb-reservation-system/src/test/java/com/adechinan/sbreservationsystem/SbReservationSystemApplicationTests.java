package com.adechinan.sbreservationsystem;

import com.adechinan.sbreservationsystem.authentication.AuthenticationDTO;
import com.adechinan.sbreservationsystem.authentication.User;
import com.adechinan.sbreservationsystem.authentication.UserRepository;
import com.adechinan.sbreservationsystem.booking.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class SbReservationSystemApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void all() throws Exception {

        // test registration
        MvcResult registrationRequest = mockMvc.perform(post("/api/auth/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        new User("aldous@huxley.book", "braveNewWorld"))))
                .andReturn();

        AuthenticationDTO registrationResponse = objectMapper.readValue(
                registrationRequest.getResponse().getContentAsString(),
                AuthenticationDTO.class);

        assertThat(registrationResponse.getMessage()).isEqualTo("User registered successfully");


        // test email already exist
        MvcResult emailExistsRequest = mockMvc.perform(
                get("/api/auth/existsByEmail/aldous@huxley.book/check"))
                .andReturn();

        Map emailExistsResponse = objectMapper.readValue(
                emailExistsRequest.getResponse().getContentAsString(),
                Map.class);

        assertThat(emailExistsResponse.get("status")).isEqualTo(true);


        // test signin
        MvcResult authRequest = mockMvc.perform(post("/api/auth/signin")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        new User("aldous@huxley.book", "braveNewWorld"))))
                .andReturn();

        AuthenticationDTO authResponse = objectMapper.readValue(
                authRequest.getResponse().getContentAsString(),
                AuthenticationDTO.class);

        assertThat(authResponse.getMessage()).isEqualTo("User authenticated successfully");


        User user = this.userRepository.findById(1L).orElseThrow();

        assertThat(user.getEmail()).isEqualTo("aldous@huxley.book");

        String token = authResponse.getBearerToken();

        // test trip creation
        LocalDate leaveDate = LocalDate.now();
        MvcResult createTripRequest = mockMvc.perform(post("/api/v1/trips")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        new TripDTO(leaveDate, "DAX", "LAX", 1L))))
                .andReturn();


        Trip trip = objectMapper.readValue(
                createTripRequest.getResponse().getContentAsString(),
                Trip.class);
        assertThat(trip.getLeaveFrom()).isEqualTo("DAX");


        // test find trip
        MockHttpServletResponse findTripResponse = mockMvc.perform(
                get("/api/v1/trips?leaveDate="+leaveDate+"&leaveFrom=DAX&to=LAX")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        List<Trip> trips = Arrays.asList(objectMapper.readValue(
                findTripResponse.getContentAsString(),
                Trip[].class));
        assertThat(trips.size()).isEqualTo(1);


        // book trip with token
        MockHttpServletResponse createReservationResponse =
                mockMvc.perform(post("/api/v1/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        new ReservationDTO(trip.getId(), user.getId()))))
                .andReturn().getResponse();

        Reservation reservation = objectMapper.readValue(
                createReservationResponse.getContentAsString(),
                Reservation.class);

        assertThat(reservation.getTrip().getLeaveFrom()).isEqualTo("DAX");


        // find my reservation with token

        MockHttpServletResponse findMyReservationResponse = mockMvc.perform(
                get("/api/v1/reservations?userId="+1)
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<ReservationDTO> reservationDTOS = Arrays.asList(objectMapper.readValue(
                findMyReservationResponse.getContentAsString(),
                ReservationDTO[].class));
        assertThat(reservationDTOS.size()).isEqualTo(1);



        // delete my reservation with token
        MockHttpServletResponse deleteReservationResponse = mockMvc.perform(
                delete("/api/v1/reservations/1")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(reservationRepository.count()).isEqualTo(0);


    }





}
