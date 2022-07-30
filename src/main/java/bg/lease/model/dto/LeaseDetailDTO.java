package bg.lease.model.dto;

import java.math.BigDecimal;

public class LeaseDetailDTO {
    private String contractNo;
    private int lineNo;
    private String vehicleNo;
    private String numberPlate;
    private BigDecimal principalExclVat;
    private BigDecimal principalInclVat;

    public LeaseDetailDTO() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public BigDecimal getPrincipalExclVat() {
        return principalExclVat;
    }

    public void setPrincipalExclVat(BigDecimal principalExclVat) {
        this.principalExclVat = principalExclVat;
    }

    public BigDecimal getPrincipalInclVat() {
        return principalInclVat;
    }

    public void setPrincipalInclVat(BigDecimal principalInclVat) {
        this.principalInclVat = principalInclVat;
    }
}
