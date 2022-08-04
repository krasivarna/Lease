package bg.lease.model;

import bg.lease.model.enums.DocumentType;
import bg.lease.model.enums.LeaseApplyType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="leaseapply")
public class LeaseApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryNo;

    @Column(name="Amount_Excl_VAT")
    private BigDecimal amountExclVAT=BigDecimal.ZERO;

    @Column(name="Pay_Amount_Incl_VAT")
    private BigDecimal payAmountInclVAT=BigDecimal.ZERO;

    @Column(name="Amount_Incl_VAT")
    private BigDecimal amountInclVAT=BigDecimal.ZERO;

    @ManyToOne
    private PayoffPlan payplanEntry;

    @Column(name="Invoice_Date")
    private LocalDate invoiceDate;

    @Column(name="Payment_Date")
    private LocalDate paymentDate;

    @Column(name="Document_No")
    private String documentNo;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    private LeaseApplyType type;

    public LeaseApplyEntity() {
    }

    public Long getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(Long entryNo) {
        this.entryNo = entryNo;
    }

    public BigDecimal getAmountExclVAT() {
        return amountExclVAT;
    }

    public void setAmountExclVAT(BigDecimal amountExclVAT) {
        this.amountExclVAT = amountExclVAT;
    }

    public BigDecimal getPayAmountInclVAT() {
        return payAmountInclVAT;
    }

    public void setPayAmountInclVAT(BigDecimal payAmountInclVAT) {
        this.payAmountInclVAT = payAmountInclVAT;
    }

    public BigDecimal getAmountInclVAT() {
        return amountInclVAT;
    }

    public void setAmountInclVAT(BigDecimal amountInclVAT) {
        this.amountInclVAT = amountInclVAT;
    }

    public PayoffPlan getPayplanEntry() {
        return payplanEntry;
    }

    public void setPayplanEntry(PayoffPlan payplanEntry) {
        this.payplanEntry = payplanEntry;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public LeaseApplyType getType() {
        return type;
    }

    public void setType(LeaseApplyType type) {
        this.type = type;
    }
}
