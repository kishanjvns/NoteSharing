package com.tech.kj.web.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private boolean isPrimaryEmail;
    private String contact;
    private boolean isPrimaryContact;
}
