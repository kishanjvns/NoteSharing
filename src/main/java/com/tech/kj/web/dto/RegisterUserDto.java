package com.tech.kj.web.dto;


import com.tech.kj.domain.Roles;
import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private boolean isPrimaryEmail;
    private String contact;
    private boolean isPrimaryContact;
    private Set<Roles> roles;

    public boolean getIsPrimaryEmail() {
        return isPrimaryEmail;
    }

    public void setIsPrimaryEmail(boolean isPrimaryEmail) {
        this.isPrimaryEmail = isPrimaryEmail;
    }

    public boolean getIsPrimaryContact() {
        return isPrimaryContact;
    }

    public void setIsPrimaryContact(boolean isPrimaryContact) {
        this.isPrimaryContact = isPrimaryContact;
    }

}
