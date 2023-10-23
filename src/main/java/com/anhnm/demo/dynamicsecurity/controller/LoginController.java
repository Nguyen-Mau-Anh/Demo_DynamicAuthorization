package com.anhnm.demo.dynamicsecurity.controller;

import com.anhnm.demo.dynamicsecurity.config.JWTUtil;
import com.anhnm.demo.dynamicsecurity.dto.AuthenticationRequest;
import com.anhnm.demo.dynamicsecurity.dto.AuthenticationResponse;
import com.anhnm.demo.dynamicsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JWTUtil jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtUtils.generateToken(user);
            AuthenticationResponse response = new AuthenticationResponse(user.getUsername(), token);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
