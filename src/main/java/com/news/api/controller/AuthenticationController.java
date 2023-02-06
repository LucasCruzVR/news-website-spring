package com.news.api.controller;

import javax.validation.Valid;

import com.news.api.model.UserAuthReqDTO;
import com.news.api.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authManager;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserAuthReqDTO userReq) {
        try {
            if (userReq.getEmail() == null || userReq.getPassword() == null) {
                throw new NullPointerException("Email and Password are required!");
            }
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userReq.getEmail(), userReq.getPassword())
            );
            UserAuthReqDTO user = (UserAuthReqDTO) authentication.getPrincipal();
            return ResponseEntity.ok().body(authenticationService.authenticationToken(user));

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Email or Password are incorrect!");
        }
    }
}
