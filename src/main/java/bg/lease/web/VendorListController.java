package bg.lease.web;

import bg.lease.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendorListController {

    private VendorService vendorService;

    public VendorListController(VendorService vendorService){
        this.vendorService=vendorService;
    }

    @GetMapping("/vendorlist")
    public String vendorList(Model model){
        model.addAttribute("vendorList",vendorService.listVendor());
        model.addAttribute("hideCard",false);
        return "vendorlist";
    }

}
