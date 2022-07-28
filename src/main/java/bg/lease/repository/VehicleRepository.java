package bg.lease.repository;

import bg.lease.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity,String> {
    Optional<VehicleEntity> findByNo(String no);

    @Query(value="Select * from vehicle v where v.number_plate like %:keyword%  or v.no like %:keyword% or v.vehicle_model like %:keyword%",nativeQuery = true)
    List<VehicleEntity> findByKeyword (@Param("keyword") String keyword);

    void deleteByNo(String vehicleNo);
}
