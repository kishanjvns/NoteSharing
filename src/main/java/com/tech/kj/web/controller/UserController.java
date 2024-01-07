package com.tech.kj.web.controller;

import com.tech.kj.service.UserService;
import com.tech.kj.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> createUser(@RequestBody UserDto userDto){
        log.info("save request received for user {}",userDto);
        Long createdId = userService.save(userDto);
        log.info("record created with id {}",createdId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id",createdId));
    }
}
