package bg.lease.repository;

import bg.lease.model.PayoffPlan;
import bg.lease.model.enums.PayOffAmountType;
import bg.lease.model.enums.PayOffEntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayOffRepository extends JpaRepository<PayoffPlan,Long> {
    int countByLeaseDetail_ContractNoAndEntryTypeAndLeaseDetail_LineNoAndCancelAndAmountType(
            String contractNo, PayOffEntryType entryType, int contractLine, boolean cancel, PayOffAmountType amountType);

    Optional<PayoffPlan> findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndMonthAndAmountTypeAndCancel(
            String contractNo,int contractLine,int month,PayOffAmountType amountType,boolean cancel);
}
