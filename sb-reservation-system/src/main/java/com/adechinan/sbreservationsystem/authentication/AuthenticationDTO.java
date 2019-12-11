package com.adechinan.sbreservationsystem.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AuthenticationDTO {
    private String bearerToken;
    private String message;
    private Long user;

    public static AuthenticationDTO fromMessage(String message, Long user){
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setMessage(message);
        authenticationDTO.setUser(user);
        return authenticationDTO;
    }

    public static AuthenticationDTO fromBearerToken(String message, String bearerToken, Long user){
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setMessage(message);
        authenticationDTO.setBearerToken(bearerToken);
        authenticationDTO.setUser(user);
        return authenticationDTO;
    }

}
