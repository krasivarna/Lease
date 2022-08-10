package bg.lease.web;

import bg.lease.service.VendorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class VendorListController {

    private VendorService vendorService;

    public VendorListController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @PreAuthorize("@globalPermissionService.VendorIsRead(#principal.name)")
    @GetMapping("/vendorlist")
    public String vendorList(Model model, Principal principal){
        model.addAttribute("vendorList",vendorService.listVendor());
        model.addAttribute("hideCard",true);
        return "vendorlist";
    }

}
