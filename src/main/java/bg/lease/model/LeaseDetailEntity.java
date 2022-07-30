package bg.lease.model;

import bg.lease.model.dto.LeaseDetailId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name="leasedetail")
@IdClass(LeaseDetailId.class)
public class LeaseDetailEntity {
    @Id
    @Size(max=20)
    @Column(name="Contract_No")
    private String contractNo;

    @Id
    @Column(name="Line_No")
    private int lineNo;

    @ManyToOne
    private VehicleEntity vehicle;

    @Column(name="Principal_Excl_Vat")
    private BigDecimal principalExclVat;

    @Column(name="Principal_Incl_Vat")
    private BigDecimal principalInclVat;

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

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
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

    public LeaseDetailEntity() {
    }
}
