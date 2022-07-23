package bg.lease.web;

import bg.lease.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class VehicleListController {

    private VehicleService vehicleService;

    public VehicleListController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehiclelist")
    public String vehicleList(Model model){
        model.addAttribute("vehicleList",vehicleService.listVehicle());
        model.addAttribute("hideCard",true);
        return "vehiclelist";
    }
}
