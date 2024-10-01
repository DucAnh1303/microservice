package com.service.microservice.auth.repository;

import com.service.microservice.data.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long>, AuthDslRepository {

    Optional<AuthEntity> findByName(String name);

}
