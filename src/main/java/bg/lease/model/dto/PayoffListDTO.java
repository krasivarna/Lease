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
}
