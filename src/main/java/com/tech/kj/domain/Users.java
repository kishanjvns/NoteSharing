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
@SecondaryTable(
        name = "user_relevance",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id"))
public class Users extends BaseEntity implements UserDetails {
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


    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRole.class)
    private Set<UserRole> roles;
    @Column(table = "user_relevance")
    private Integer relevance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        System.out.println(emails);
        return  emails.stream().filter(e-> e.isPrimary()).map(e->e.getEmail()).findFirst().get();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
