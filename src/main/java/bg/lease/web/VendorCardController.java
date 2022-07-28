package bg.lease.web;

import bg.lease.model.dto.VendorDTO;
import bg.lease.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class VendorCardController {
    private VendorService vendorService;

    public VendorCardController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @GetMapping("/vendorcard")
    public String vendorCard(Model model) {
        model.addAttribute("vendorDTO",new VendorDTO());
        model.addAttribute("hideCard",false);
        return "vendorlist";
    }

    @PostMapping("/vendorcard")
    public String vendorCard(@Valid VendorDTO vendorDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vendorDTO",vendorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vendorDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/vendorcard";
        }
        vendorService.addCard(vendorDTO);
        return "redirect:/vendorlist";
    }

    @GetMapping("/vendorcard/{code}")
    public String editVendorCard(Model model, @PathVariable("code") String vendorNo){
        VendorDTO vendor=this.vendorService.editCard(vendorNo);
        model.addAttribute("vendorDTO",vendor);
        model.addAttribute("hideCard",false);
        return "vendorlist";
    }

    @GetMapping("/deletevendorcard/{code}")
    public String deleteVendorCard(Model model, @PathVariable("code") String vendorNo){
        model.addAttribute("hideCard",false);
        this.vendorService.deleteCard(vendorNo);
        return "redirect:/vendorlist";
    }
}
