package bg.lease.web;

import bg.lease.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class VendorListController {

    private VendorService vendorService;

    public VendorListController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @ModelAttribute
    public void initForm(Model model){
        model.addAttribute("vendorList",vendorService.listVendor());
    }

    @GetMapping("/vendorlist")
    public String vendorList(){
        return "vendorlist";
    }
}
