package bg.lease.web;

import bg.lease.model.dto.VehicleDTO;
import bg.lease.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class VehicleCardController {

    private VehicleService vehicleService;

    public VehicleCardController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ModelAttribute
    public void initForm(Model model){
        model.addAttribute("vehicleDTO",new VehicleDTO());
    }

    @GetMapping("/vehiclecard")
    public String vehicleCard() {
        return "vehiclecard";
    }

    @PostMapping("/vehiclecard")
    public String vehicleCard(@Valid VehicleDTO vehicleDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vehicleDTO",vehicleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vehicleDTO",bindingResult);
            return "redirect:/vehiclecard";
        }
        vehicleService.addCard(vehicleDTO);
        return "redirect:/vehiclelist";
    }

    @GetMapping("/vehiclecard/{code}")
    public String editVehicleCard(Model model, @PathVariable("code") String vehicleNo){
        VehicleDTO vehicle=this.vehicleService.editCard(vehicleNo);
        model.addAttribute("vehicleDTO",vehicle);
        return "vehiclecard";
    }
}
