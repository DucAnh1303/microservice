package com.service.microservice.batch.repository.mysql;


import com.service.microservice.batch.entities.mysql.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<InformationEntity, Long> {
}
