package com.tech.kj.web.controller;


import com.tech.kj.config.security.JwtTokenProvider;
import com.tech.kj.domain.Contacts;
import com.tech.kj.domain.Emails;
import com.tech.kj.domain.Users;
import com.tech.kj.repository.UserRepository;
import com.tech.kj.web.dto.JwtAccessTokenResponse;
import com.tech.kj.web.dto.LoginDto;
import com.tech.kj.web.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userJpaRepository;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder,
            UserRepository userJpaRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userJpaRepository = userJpaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAccessTokenResponse> login(@RequestBody LoginDto loginDto) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(JwtAccessTokenResponse.builder().access_token(token).build());
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<JwtAccessTokenResponse> register(
            @RequestBody RegisterUserDto request) {
        Contacts contactEntity = Contacts.builder()
                .isVerified(true)
                .isPrimary(request.getIsPrimaryContact())
                .contact(request.getContact())
                .build();
        Emails emailEntity = Emails.builder()
                .isVerified(true)
                .isPrimary(request.getIsPrimaryEmail())
                .email(request.getEmail())
                .build();
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .contacts(Set.of(contactEntity))
                .emails(Set.of(emailEntity))
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .build();
        contactEntity.setUser(user);
        emailEntity.setUser(user);
        this.userJpaRepository.save(user);
        return ResponseEntity.created(URI.create("/api/v1/auth/login")).build();
    }
}