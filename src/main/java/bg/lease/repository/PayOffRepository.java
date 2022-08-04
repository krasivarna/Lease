package bg.lease.repository;

import bg.lease.model.PayoffPlan;
import bg.lease.model.enums.PayOffAmountType;
import bg.lease.model.enums.PayOffEntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayOffRepository extends JpaRepository<PayoffPlan,Long> {
    int countByLeaseDetail_ContractNoAndEntryTypeAndLeaseDetail_LineNoAndCancelAndAmountType(
            String contractNo, PayOffEntryType entryType, int contractLine, boolean cancel, PayOffAmountType amountType);

    Optional<PayoffPlan> findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndMonthAndAmountTypeAndCancel(
            String contractNo,int contractLine,int month,PayOffAmountType amountType,boolean cancel);

    List<PayoffPlan> findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancel(String contractNo, int lineNo, boolean cancel);

    boolean existsByLeaseDetail_ContractNoAndInvoicedPrincipalAndCancel(String contractNo,boolean invoicedPrincipal,boolean cancel);

    boolean existsByLeaseDetail_ContractNoAndInvoicedInterestAndCancel(String contractNo,boolean invoicedInterest,boolean cancel);

    void deleteByLeaseDetail_ContractNo(String contractNo);

    List<PayoffPlan> findByLeaseDetail_ContractNoOrderByLeaseDetail_ContractNoAscMonthAsc(String searchKey);

    List<PayoffPlan> findAll();

    Optional<PayoffPlan> findByEntryNo(Long entryNo);
}
