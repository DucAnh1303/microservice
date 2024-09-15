package com.service.employee.repository;

import com.data.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthEntity, Long> {

    Optional<AuthEntity> findByName(String name);
}
