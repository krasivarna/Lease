package bg.lease.repository;

import bg.lease.model.LeaseHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaseRepository extends JpaRepository<LeaseHeaderEntity,String> {
    Optional<LeaseHeaderEntity> findByContractNo(String contractNo);
}
