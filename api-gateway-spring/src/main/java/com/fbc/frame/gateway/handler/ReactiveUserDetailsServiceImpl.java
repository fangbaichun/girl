package com.fbc.frame.gateway.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Mono<UserDetails> findByUsername(String username) {

        User admin = new User("admin", passwordEncoder.encode("123456"), new ArrayList<>());
        Mono<UserDetails> userMono = Mono.justOrEmpty(admin);
        return userMono;
    }
}
