package com.service.microservice.manage.repository;

import com.service.microservice.manage.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, AccountDslRepository {

    Optional<AccountEntity> findByIdAuth(Long id);
}
