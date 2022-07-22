package bg.lease.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="vehicle")
public class VehicleEntity {

    @Id
    @Size(max=10)
    @Column(name="No")
    private String no;

    @Size(max=10)
    @Column(name="Number_Plate")
    private String numberPlate;

    @Size(max=30)
    @Column(name="Vehicle_Model")
    private String vehicleModel;

    public VehicleEntity() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
