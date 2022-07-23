package bg.lease.web;

import bg.lease.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaseListController {
    private LeaseService leaseService;

    public LeaseListController(LeaseService leaseService){
        this.leaseService=leaseService;
    }

    @GetMapping("/leasinglist")
    public String leaseList(Model model){
        model.addAttribute("ListLease",leaseService.listLease());
        model.addAttribute("hideCard",true);
        return "leaselist";
    }
}
