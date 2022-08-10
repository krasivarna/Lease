package bg.lease.web;

import bg.lease.service.LeaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LeaseListController {
    private LeaseService leaseService;

    public LeaseListController(LeaseService leaseService){
        this.leaseService=leaseService;
    }

    @PreAuthorize("@globalPermissionService.LeaseIsRead(#principal.name)")
    @GetMapping("/leasinglist")
    public String leaseList(Model model, Principal principal){
        model.addAttribute("ListLease",leaseService.listLease());
        model.addAttribute("showList",true);
        model.addAttribute("showCard",false);
        model.addAttribute("showDetailCard",false);
        return "leaselist";
    }
}
