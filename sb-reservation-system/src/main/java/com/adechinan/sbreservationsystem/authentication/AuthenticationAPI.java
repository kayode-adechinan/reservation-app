package com.adechinan.sbreservationsystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticationAPI {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/signin")
    public AuthenticationDTO signin(@Valid @RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       user.getEmail(),
                       user.getPassword()
                )
        );

        User userRetrieved =userRepository.findByUsernameOrEmail(user.getEmail(), user.getEmail()).orElseThrow();


        SecurityContextHolder.getContext().setAuthentication(authentication);




       return AuthenticationDTO.fromBearerToken("User authenticated successfully",
               tokenProvider.generateToken(authentication), userRetrieved.getId());



    }



    @PostMapping("/signup")
    public AuthenticationDTO signup(@Valid @RequestBody User userInput) {

        User user = new User(userInput.getEmail(), userInput.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User userSaved = userRepository.save(user);

        return AuthenticationDTO.fromMessage("User registered successfully", userSaved.getId());


    }



    @GetMapping("/existsByEmail/{email}/check")
    public Map<String, Boolean> existsByEmail(@PathVariable String email){
        return  Map.of("status", this.userRepository.existsByEmail(email));
    }


}

