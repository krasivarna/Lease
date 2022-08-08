package bg.lease.model.dto;

import javax.validation.constraints.NotEmpty;

public class VehicleDTO {
    @NotEmpty
    private String no;
    private String numberPlate;
    private String vehicleModel;

    public VehicleDTO() {
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
