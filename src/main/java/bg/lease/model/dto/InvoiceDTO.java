package bg.lease.model.dto;

import bg.lease.model.enums.DocumentType;
import bg.lease.model.enums.LeaseApplyType;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class InvoiceDTO {

    private Long entryNo;

    @NotEmpty(message="Document no is missing.")
    private String documentNo;

    private DocumentType documentType;

    private LeaseApplyType type;

    @NotNull()
    private BigDecimal amountExclVAT;

    @NotNull()
    private BigDecimal amountInclVAT;

    private Long payplanEntry;

    public InvoiceDTO() {
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

    public BigDecimal getAmountExclVAT() {
        return amountExclVAT;
    }

    public void setAmountExclVAT(BigDecimal amountExclVAT) {
        this.amountExclVAT = amountExclVAT;
    }

    public BigDecimal getAmountInclVAT() {
        return amountInclVAT;
    }

    public void setAmountInclVAT(BigDecimal amountInclVAT) {
        this.amountInclVAT = amountInclVAT;
    }

    public Long getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(Long entryNo) {
        this.entryNo = entryNo;
    }

    public Long getPayplanEntry() {
        return payplanEntry;
    }

    public void setPayplanEntry(Long payplanEntry) {
        this.payplanEntry = payplanEntry;
    }
}
