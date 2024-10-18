package com.service.microservice.auth.repository;

import com.service.microservice.auth.entities.AuthControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthControlRepository extends JpaRepository<AuthControlEntity, Long> {

}
