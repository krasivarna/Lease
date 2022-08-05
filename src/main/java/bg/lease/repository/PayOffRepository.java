package bg.lease.repository;

import bg.lease.model.PayoffPlan;
import bg.lease.model.enums.PayOffAmountType;
import bg.lease.model.enums.PayOffEntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    List<PayoffPlan> findByLeaseDetail_ContractNo(String searchKey);

    List<PayoffPlan> findAll();

    Optional<PayoffPlan> findByEntryNo(Long entryNo);

    Optional<PayoffPlan> findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndDueDateLessThanOrderByDueDateDesc(
            String contractNo, int lineNo, boolean cancel, PayOffAmountType amountType, LocalDate dueDate);

    Optional<PayoffPlan> findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualAndInvoicedInterestOrderByMonthDesc(
            String contractNo, int lineNo, boolean cancel, PayOffAmountType amountType,int month,boolean invoicedInterest);

    Optional<PayoffPlan> findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualAndEntryTypeOrderByMonthDesc(
            String contractNo, int lineNo, boolean cancel, PayOffAmountType amountType,int month,PayOffEntryType entryType);

    Optional<PayoffPlan> findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualOrderByMonthDesc(
            String contractNo, int lineNo, boolean cancel, PayOffAmountType amountType,int month);

    List<PayoffPlan> findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqual(
            String contractNo, int lineNo, boolean cancel, PayOffAmountType amountType,int month);
}
