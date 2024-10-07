package com.service.microservice.syncdb.repository;

import com.service.microservice.syncdb.entities.mysql.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlRepository extends JpaRepository<VendorEntity,Long> {

}
