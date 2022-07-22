package bg.lease.model.dto;

import java.time.LocalDate;

public class LeaseDTO {
    private String contractNo;
    private LocalDate contractDate;
    private VendorSmallDTO vendor;

    public LeaseDTO() {
    }

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

    public VendorSmallDTO getVendor() {
        return vendor;
    }

    public void setVendor(VendorSmallDTO vendor) {
        this.vendor = vendor;
    }
}
