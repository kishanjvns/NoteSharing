package com.tech.kj.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent failure) {
        log.info("my event "+failure.getAuthentication().get());
        log.info("my event "+failure.getAuthorizationDecision());
    }
}