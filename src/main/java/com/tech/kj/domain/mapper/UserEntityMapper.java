package com.tech.kj.domain.mapper;

import com.tech.kj.domain.Contacts;
import com.tech.kj.domain.Emails;
import com.tech.kj.domain.Users;
import com.tech.kj.web.dto.UserDto;

import java.util.Set;

public class UserEntityMapper {

    public static Users mapToEntity(UserDto userDto) {
        return  Users.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .isDeleted(false)
                .emails(Set.of(Emails.builder()
                        .email(userDto.getEmail())
                        .isPrimary(userDto.isPrimaryEmail())
                        //TODO take isVerified from request, in future
                        .isVerified(true)
                        .build()))
                .contacts(Set.of(Contacts.builder()
                        .contact(userDto.getContact())
                        .isPrimary(userDto.isPrimaryContact())
                        .isVerified(true)
                        .build()))
                .build();
    }
}
