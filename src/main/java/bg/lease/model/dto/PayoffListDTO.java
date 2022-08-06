package bg.lease.model.dto;

import bg.lease.model.LeaseDetailEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayoffListDTO {
    private Long entryNo;
    private LocalDate dueDate;
    private int month;
    private BigDecimal principalExclVAT;
    private BigDecimal interestExclVAT;
    private LeaseDetailEntity detail;
    private BigDecimal remainingPrincipalExclVAT;
    private BigDecimal remainingPrincipalInclVAT;
    private boolean invoicedPrincipal;
    private boolean payedPrincipal;
    private boolean invoicedInterest;
    private boolean payedInterest;

    public PayoffListDTO() {
    }

    public Long getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(Long entryNo) {
        this.entryNo = entryNo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getPrincipalExclVAT() {
        return principalExclVAT;
    }

    public void setPrincipalExclVAT(BigDecimal principalExclVAT) {
        this.principalExclVAT = principalExclVAT;
    }

    public BigDecimal getInterestExclVAT() {
        return interestExclVAT;
    }

    public void setInterestExclVAT(BigDecimal interestExclVAT) {
        this.interestExclVAT = interestExclVAT;
    }

    public LeaseDetailEntity getDetail() {
        return detail;
    }

    public void setDetail(LeaseDetailEntity detail) {
        this.detail = detail;
    }

    public BigDecimal getRemainingPrincipalExclVAT() {
        return remainingPrincipalExclVAT;
    }

    public void setRemainingPrincipalExclVAT(BigDecimal remainingPrincipalExclVAT) {
        this.remainingPrincipalExclVAT = remainingPrincipalExclVAT;
    }

    public BigDecimal getRemainingPrincipalInclVAT() {
        return remainingPrincipalInclVAT;
    }

    public void setRemainingPrincipalInclVAT(BigDecimal remainingPrincipalInclVAT) {
        this.remainingPrincipalInclVAT = remainingPrincipalInclVAT;
    }

    public boolean isInvoicedPrincipal() {
        return invoicedPrincipal;
    }

    public void setInvoicedPrincipal(boolean invoicedPrincipal) {
        this.invoicedPrincipal = invoicedPrincipal;
    }

    public boolean isPayedPrincipal() {
        return payedPrincipal;
    }

    public void setPayedPrincipal(boolean payedPrincipal) {
        this.payedPrincipal = payedPrincipal;
    }

    public boolean isInvoicedInterest() {
        return invoicedInterest;
    }

    public void setInvoicedInterest(boolean invoicedInterest) {
        this.invoicedInterest = invoicedInterest;
    }

    public boolean isPayedInterest() {
        return payedInterest;
    }

    public void setPayedInterest(boolean payedInterest) {
        this.payedInterest = payedInterest;
    }
}
