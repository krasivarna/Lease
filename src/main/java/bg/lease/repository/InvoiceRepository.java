package bg.lease.repository;

import bg.lease.model.LeaseApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<LeaseApplyEntity,Long> {

    List<LeaseApplyEntity> findAll();
}
