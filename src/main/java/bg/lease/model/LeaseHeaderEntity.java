package bg.lease.model;

import bg.lease.model.enums.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="leaseheader")
public class LeaseHeaderEntity {
    @Id
    @Size(max=20)
    @Column(name="Contract_No",nullable = false)
    private String contractNo;

    @Column(name="Contract_Date")
    private LocalDate contractDate;

    @ManyToOne
    @JoinColumn(name="Vendor_No")
    private VendorEntity vendorNo;

    @Column(name="Principal_Period")
    @ColumnDefault("0")
    private Integer principalPeriod;

    @Size(max=20)
    @Column(name="Dimension")
    @ColumnDefault("''")
    private String dimension;

    @Enumerated(EnumType.STRING)
    @Column(name="Payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name="Invoice_type")
    private InvoiceType invoiceType;

    @Enumerated(EnumType.STRING)
    @Column(name="Loan_paid")
    private LoanPaid loanPaid;

    @Enumerated(EnumType.STRING)
    @Column(name="Loan_type")
    private LoanType loanType;

    @Column(name="No_first_month")
    @ColumnDefault("0")
    private int noFirstMonth;

    @Column(name="Interest_with_VAT")
    @ColumnDefault("false")
    private boolean interestWithVAT=false;

    @Column(name="Date_modify_percent")
    private LocalDate dateModifyPercent;

    @Size(max=10)
    @Column(name="Currency_code")
    @ColumnDefault("''")
    private String currencyCode;

    @Enumerated(EnumType.STRING)
    @Column(name="Lease_status")
    private LeaseStatus leaseStatus;

    @Column(name="Begin_PayOff_Date")
    private LocalDate beginPayOffDate;

    @Column(name="Principal_Interest")
    @ColumnDefault("0")
    private BigDecimal principalInterest;

    @Column(name="Principal_Excl_VAT")
    @ColumnDefault("0")
    private BigDecimal principalExclVAT;

    @Column(name="Principal_Incl_VAT")
    @ColumnDefault("0")
    private BigDecimal principalInclVAT;

    @Column(name="Begin_Payment_Excl_VAT")
    @ColumnDefault("0")
    private BigDecimal beginPaymentExclVAT;

    @Column(name="Begin_Payment_Incl_VAT")
    @ColumnDefault("0")
    private BigDecimal beginPaymentInclVAT;

    @Column(name="Principal_Garce_Period")
    @ColumnDefault("0")
    private int principalGracePeriod;

//-----------------------------

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

    public VendorEntity getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(VendorEntity vendorNo) {
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

    public LeaseHeaderEntity() {
    }
}
