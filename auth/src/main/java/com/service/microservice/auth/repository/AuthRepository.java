package com.service.microservice.auth.repository;

import com.service.microservice.auth.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long>, AuthDslRepository {


    Optional<AuthEntity> findByName(String name);

    Optional<AuthEntity> findByEmail(String email);

    Optional<AuthEntity> findByEmailAndName(String email, String name);

}
