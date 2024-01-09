package com.tech.kj.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CLIENT("ROLE_ADMIN");
    private final  String name;
    UserRole(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String getAuthority() {
        return toString();
    }
}