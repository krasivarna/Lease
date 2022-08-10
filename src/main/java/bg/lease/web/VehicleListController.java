package bg.lease.web;

import bg.lease.service.VehicleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class VehicleListController {

    private VehicleService vehicleService;

    public VehicleListController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("@globalPermissionService.VehicleIsRead(#principal.name)")
    @GetMapping("/vehiclelist")
    public String vehicleList(Model model, Principal principal){
        model.addAttribute("vehicleList",vehicleService.listVehicle());
        model.addAttribute("hideCard",true);
        return "vehiclelist";
    }
}
