package com.dipComputer.Dip.Computer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final String jwtSecret;

    public AdminController(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @PostMapping("/login")
    public String login(Authentication authentication) {
        String username = authentication.getName();
        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", authentication.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
        return "Bearer " + token;
    }
}
