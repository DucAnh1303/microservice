package service.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.vendor.entity.VendorEntity;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}
