package com.news.api.controller;

import com.news.api.dto.UserAuthReqDTO;
import com.news.api.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authManager;

    @GetMapping("/login")
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

        } catch (RuntimeException ex) {
            Map<String, String> map = new HashMap<>();
            map.put("error", "Email or Password are incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }
}
