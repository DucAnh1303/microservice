package service.vendor.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import service.vendor.entity.VendorEntity;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    @Query("select n from VendorEntity n where n.name like concat('%',:name,'%') ")
    Page<VendorEntity> findAll(@Param("name") String name, Pageable pageable);

}
