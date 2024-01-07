package com.tech.kj.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<String>> hello(){
        return ResponseEntity.status(HttpStatus.OK).body(Optional.of("hello"));
    }
}
