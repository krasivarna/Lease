package bg.lease.repository;

import bg.lease.model.LeaseDetailEntity;
import bg.lease.model.dto.LeaseDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseDetailRepository extends JpaRepository<LeaseDetailEntity, LeaseDetailId> {
    List<LeaseDetailEntity> findByContractNo(String contractNo);
}
