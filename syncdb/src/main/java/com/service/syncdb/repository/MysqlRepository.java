package com.service.syncdb.repository;

import com.service.syncdb.entities.mysql.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlRepository extends JpaRepository<VendorEntity,Long> {

}
