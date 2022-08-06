package bg.lease.model;

import bg.lease.model.enums.PayOffAmountType;
import bg.lease.model.enums.PayOffEntryType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="payoffplan")
public class PayoffPlan {
    @Id
    @Column(name="Entry_No")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryNo;

    @Column(name="Due_Date")
    private LocalDate dueDate;

    @Column(name="Month")
    private int month;

    @Column(name="Invoiced_Principal")
    private boolean invoicedPrincipal;

    @Column(name="Principal_Excl_VAT")
    private BigDecimal principalExclVAT;

    @Column(name="Interest_Ecl_VAT")
    private BigDecimal interestExclVAT;

    @Column(name="Payed_Principal")
    private boolean payedPrincipal;

    @Enumerated(EnumType.STRING)
    @Column(name="Entry_Type")
    private PayOffEntryType entryType;

    @Enumerated(EnumType.STRING)
    @Column(name="AMount_Type")
    private PayOffAmountType amountType;

    @Column(name="Principal_Incl_VAT")
    private BigDecimal principalInclVAT;

    @Column(name="Interest_Incl_VAT")
    private BigDecimal interestInclVAT;

    @Column(name="Invocied_Intrest")
    private boolean invoicedInterest;

    @Column(name="Payed_Interest")
    private boolean payedInterest;

    private boolean open;

    @Column(name="Remaining_Principal_Excl_VAT")
    private BigDecimal remainingPrincipalExclVAT;

    @Column(name="Remaining_Principal_Incl_VAT")
    private BigDecimal remainingPrincipalInclVAT;

    private boolean error;

    private boolean cancel;

    private boolean closed;

    private boolean early;

    @Column(name="Last_Payment")
    private boolean lastPayment;

    @ManyToOne
    private LeaseDetailEntity leaseDetail;

    @OneToMany
    private List<LeaseApplyEntity> applyList;

    public PayoffPlan() {
    }

    public void createPayoffPlan(int month,PayOffAmountType amountType,PayOffEntryType entryType){
        this.cancel=false;
        this.closed=false;
        this.early=false;
        this.error=false;
        this.lastPayment=false;
        this.payedPrincipal=false;
        this.payedInterest=false;
        this.interestExclVAT=BigDecimal.ZERO;
        this.interestInclVAT=BigDecimal.ZERO;
        this.principalExclVAT=BigDecimal.ZERO;
        this.principalInclVAT=BigDecimal.ZERO;
        this.remainingPrincipalExclVAT=BigDecimal.ZERO;
        this.remainingPrincipalInclVAT=BigDecimal.ZERO;
        this.month=month;
        this.amountType=amountType;
        this.entryType=entryType;
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

    public boolean isInvoicedPrincipal() {
        return invoicedPrincipal;
    }

    public void setInvoicedPrincipal(boolean invoicedPrincipal) {
        this.invoicedPrincipal = invoicedPrincipal;
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

    public boolean isPayedPrincipal() {
        return payedPrincipal;
    }

    public void setPayedPrincipal(boolean payedPrincipal) {
        this.payedPrincipal = payedPrincipal;
    }

    public PayOffEntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(PayOffEntryType entryType) {
        this.entryType = entryType;
    }

    public PayOffAmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(PayOffAmountType amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getPrincipalInclVAT() {
        return principalInclVAT;
    }

    public void setPrincipalInclVAT(BigDecimal principalInclVAT) {
        this.principalInclVAT = principalInclVAT;
    }

    public BigDecimal getInterestInclVAT() {
        return interestInclVAT;
    }

    public void setInterestInclVAT(BigDecimal interestInclVAT) {
        this.interestInclVAT = interestInclVAT;
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isEarly() {
        return early;
    }

    public void setEarly(boolean early) {
        this.early = early;
    }

    public boolean isLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(boolean lastPayment) {
        this.lastPayment = lastPayment;
    }

    public LeaseDetailEntity getLeaseDetail() {
        return leaseDetail;
    }

    public void setLeaseDetail(LeaseDetailEntity leaseDetail) {
        this.leaseDetail = leaseDetail;
    }
}
