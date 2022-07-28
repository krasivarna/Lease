package bg.lease.web;

import bg.lease.model.dto.VehicleDTO;
import bg.lease.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/vehiclecard")
    public String vehicleCard(Model model) {
        model.addAttribute("vehicleDTO",new VehicleDTO());
        model.addAttribute("hideCard",false);
        return "vehiclelist";
    }

    @PostMapping("/vehiclecard")
    public String vehicleCard(@Valid VehicleDTO vehicleDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vehicleDTO",vehicleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vehicleDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/vehiclecard";
        }
        vehicleService.addCard(vehicleDTO);
        return "redirect:/vehiclelist";
    }

    @GetMapping("/vehiclecard/{code}")
    public String editVehicleCard(Model model, @PathVariable("code") String vehicleNo){
        VehicleDTO vehicle=this.vehicleService.editCard(vehicleNo);
        model.addAttribute("vehicleDTO",vehicle);
        model.addAttribute("hideCard",false);
        return "vehiclelist";
    }

    @GetMapping("/deletevehiclecard/{code}")
    public String deleteVehicleCard(Model model, @PathVariable("code") String vehicleNo){
        model.addAttribute("hideCard",false);
        this.vehicleService.deleteCard(vehicleNo);
        return "redirect:/vehiclelist";
    }
}
