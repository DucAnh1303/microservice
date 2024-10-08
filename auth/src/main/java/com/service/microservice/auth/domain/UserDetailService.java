package com.service.microservice.auth.domain;

import com.service.microservice.auth.repository.AuthRepository;
import com.service.microservice.auth.entities.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AuthEntity> auth = authRepository.findByName(username);
        if (auth.isEmpty()) {
            throw new UsernameNotFoundException("User name is not valid");
        }
        return new User(auth.get().getName(), auth.get().getPassword(), Collections.emptyList());
    }
}
