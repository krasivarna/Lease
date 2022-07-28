package bg.lease.repository;

import bg.lease.model.LeaseDetailEntity;
import bg.lease.model.dto.LeaseDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeaseDetailRepository extends JpaRepository<LeaseDetailEntity, LeaseDetailId> {
    List<LeaseDetailEntity> findByContractNo(String contractNo);

    Optional<LeaseDetailEntity> findByContractNoAndLineNo(String contractNo,int lineNo);

    @Query(value="select * from leasedetail v where v.contract_No=:contractNo order by v.line_no desc limit 1",nativeQuery = true)
    Optional<LeaseDetailEntity> findLastLineNo(@Param("contractNo") String contractNo);

    void deleteByContractNoAndLineNo(String contractNo, int lineNo);

    void deleteByContractNo(String contractNo);
}
