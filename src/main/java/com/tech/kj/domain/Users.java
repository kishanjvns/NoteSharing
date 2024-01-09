package com.tech.kj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users extends BaseEntity{
    private String firstName;
    private String lastName;
    private String middleName;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Contacts> contacts = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Emails> emails = new HashSet<>();

    @Column(unique = true)
    private String userName;

    private String password;

    private boolean isDeleted = false;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Roles> roles;

}
