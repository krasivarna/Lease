package bg.lease.web;

import bg.lease.model.dto.VehicleDTO;
import bg.lease.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleRestController {

    private VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehiclesmalllist")
    public ResponseEntity<List<VehicleDTO>> smallList(@RequestParam(name = "key",defaultValue = "") String searchKey){
        return ResponseEntity.ok(this.vehicleService.smallListVehicle(searchKey));
    }
}
