package bg.lease.repository;

import bg.lease.model.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<VendorEntity,String> {
    Optional<VendorEntity> findByNo(String no);

    @Query(value="Select * from vendor v where v.Name like %:keyword%  or v.vat_registration like %:keyword% or v.no like %:keyword%",nativeQuery = true)
    List<VendorEntity> findByKeyword (@Param("keyword") String keyword);
}
