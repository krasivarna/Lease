package bg.lease.repository;

import bg.lease.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity,String> {
    Optional<VehicleEntity> findByNo(String no);
}
