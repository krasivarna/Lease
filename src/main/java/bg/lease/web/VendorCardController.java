package bg.lease.web;

import bg.lease.model.dto.VendorDTO;
import bg.lease.service.VendorService;
import bg.lease.util.TransformErrors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class VendorCardController {
    private VendorService vendorService;
    private TransformErrors transformErrors;

    public VendorCardController(VendorService vendorService,
                                TransformErrors transformErrors){
        this.vendorService=vendorService;
        this.transformErrors = transformErrors;
    }

    @PreAuthorize("@globalPermissionService.VendorIsInsert(#principal.name)")
    @GetMapping("/vendorcard")
    public String vendorCard(Model model,Principal principal) {
        model.addAttribute("vendorDTO",new VendorDTO());
        model.addAttribute("hideCard",false);
        return "vendorlist";
    }

    @PreAuthorize("@globalPermissionService.VendorIsInsert(#principal.name)")
    @PostMapping("/vendorcard")
    public String vendorCard(@Valid VendorDTO vendorDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vendorDTO",vendorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vendorDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            redirectAttributes.addFlashAttribute("listErrors",transformErrors.listOfErrors(bindingResult.getFieldErrors()));
            redirectAttributes.addFlashAttribute("hasError",true);
            return "redirect:/vendorcard";
        }
        try {
            vendorService.addCard(vendorDTO);
        } catch (RuntimeException e){
            bindingResult.addError(new FieldError("","",e.getMessage()));
            redirectAttributes.addFlashAttribute("vendorDTO",vendorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vendorDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            redirectAttributes.addFlashAttribute("listErrors",transformErrors.listOfErrors(bindingResult.getFieldErrors()));
            redirectAttributes.addFlashAttribute("hasError",true);
            return "redirect:/vendorcard";
        }
        return "redirect:/vendorlist";
    }

    @PreAuthorize("@globalPermissionService.VendorIsRead(#principal.name)")
    @GetMapping("/vendorcard/{code}")
    public String editVendorCard(Model model, @PathVariable("code") String vendorNo,Principal principal){
        VendorDTO vendor=this.vendorService.editCard(vendorNo);
        model.addAttribute("vendorDTO",vendor);
        model.addAttribute("hideCard",false);
        return "vendorlist";
    }

    @PreAuthorize("@globalPermissionService.VendorIsDelete(#principal.name)")
    @GetMapping("/deletevendorcard/{code}")
    public String deleteVendorCard(Model model, @PathVariable("code") String vendorNo,Principal principal){
        model.addAttribute("hideCard",false);
        this.vendorService.deleteCard(vendorNo);
        return "redirect:/vendorlist";
    }
}
