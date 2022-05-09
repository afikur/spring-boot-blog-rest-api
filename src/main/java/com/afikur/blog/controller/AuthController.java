package com.afikur.blog.controller;

import com.afikur.blog.dto.AuthenticationRequest;
import com.afikur.blog.dto.AuthenticationResponse;
import com.afikur.blog.service.CustomUserDetailsService;
import com.afikur.blog.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/signin")
    public AuthenticationResponse authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.usernameOrEmail(), authenticationRequest.password())
        );

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.usernameOrEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
