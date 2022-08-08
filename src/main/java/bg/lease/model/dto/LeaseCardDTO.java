package bg.lease.model.dto;

import bg.lease.model.enums.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeaseCardDTO {

    @Size(max=20)
    @Column(nullable = false)
    private String contractNo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate contractDate;

    @Positive(message="Is not positive.")
    private Integer principalPeriod;

    @Size(max=20)
    @NotNull(message = "The vendor code is missing.")
    @NotEmpty(message="The vendor code is missing.")
    private String vendorNo;

    @Size(max=20)
    private String dimension;

    @Column(nullable = false)
    @NotNull(message="Not allowed empty.")
    private PaymentType paymentType;

    @Column(nullable = false)
    @NotNull(message="Not allowed empty.")
    private InvoiceType invoiceType;

    @Column(nullable = false)
    @NotNull(message="Not allowed empty.")
    private LoanPaid loanPaid;

    @Column(nullable = false)
    @NotNull(message="Not allowed empty.")
    private LoanType loanType;

    @Positive(message="User must enter positive value.")
    private int noFirstMonth;

    private boolean interestWithVAT;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateModifyPercent;

    @Size(max=10)
    private String currencyCode;

    @Column(nullable = false)
    private LeaseStatus leaseStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    @NotNull()
    private LocalDate beginPayOffDate;

    @Positive(message="User must enter positive value.")
    private BigDecimal principalInterest;

    @Positive(message="User must enter positive value.")
    private BigDecimal principalExclVAT;

    @Positive(message="User must enter positive value.")
    private BigDecimal principalInclVAT;

    @PositiveOrZero
    private BigDecimal beginPaymentExclVAT;

    @PositiveOrZero
    private BigDecimal beginPaymentInclVAT;

    @PositiveOrZero
    private int principalGracePeriod;
//---------------------------

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Integer getPrincipalPeriod() {
        return principalPeriod;
    }

    public void setPrincipalPeriod(Integer principalPeriod) {
        this.principalPeriod = principalPeriod;
    }

    public String getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public LoanPaid getLoanPaid() {
        return loanPaid;
    }

    public void setLoanPaid(LoanPaid loanPaid) {
        this.loanPaid = loanPaid;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public int getNoFirstMonth() {
        return noFirstMonth;
    }

    public void setNoFirstMonth(int noFirstMonth) {
        this.noFirstMonth = noFirstMonth;
    }

    public boolean isInterestWithVAT() {
        return interestWithVAT;
    }

    public void setInterestWithVAT(boolean interestWithVAT) {
        this.interestWithVAT = interestWithVAT;
    }

    public LocalDate getDateModifyPercent() {
        return dateModifyPercent;
    }

    public void setDateModifyPercent(LocalDate dateModifyPercent) {
        this.dateModifyPercent = dateModifyPercent;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LeaseStatus getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(LeaseStatus leaseStatus) {
        this.leaseStatus = leaseStatus;
    }

    public LocalDate getBeginPayOffDate() {
        return beginPayOffDate;
    }

    public void setBeginPayOffDate(LocalDate beginPayOffDate) {
        this.beginPayOffDate = beginPayOffDate;
    }

    public BigDecimal getPrincipalInterest() {
        return principalInterest;
    }

    public void setPrincipalInterest(BigDecimal principalInterest) {
        this.principalInterest = principalInterest;
    }

    public BigDecimal getPrincipalExclVAT() {
        return principalExclVAT;
    }

    public void setPrincipalExclVAT(BigDecimal principalExclVAT) {
        this.principalExclVAT = principalExclVAT;
    }

    public BigDecimal getPrincipalInclVAT() {
        return principalInclVAT;
    }

    public void setPrincipalInclVAT(BigDecimal principalInclVAT) {
        this.principalInclVAT = principalInclVAT;
    }

    public BigDecimal getBeginPaymentExclVAT() {
        return beginPaymentExclVAT;
    }

    public void setBeginPaymentExclVAT(BigDecimal beginPaymentExclVAT) {
        this.beginPaymentExclVAT = beginPaymentExclVAT;
    }

    public BigDecimal getBeginPaymentInclVAT() {
        return beginPaymentInclVAT;
    }

    public void setBeginPaymentInclVAT(BigDecimal beginPaymentInclVAT) {
        this.beginPaymentInclVAT = beginPaymentInclVAT;
    }

    public int getPrincipalGracePeriod() {
        return principalGracePeriod;
    }

    public void setPrincipalGracePeriod(int principalGracePeriod) {
        this.principalGracePeriod = principalGracePeriod;
    }

    public LeaseCardDTO(){}
}
