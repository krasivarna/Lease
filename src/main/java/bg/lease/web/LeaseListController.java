package bg.lease.web;

import bg.lease.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LeaseListController {
    private LeaseService leaseService;

    public LeaseListController(LeaseService leaseService){
        this.leaseService=leaseService;
    }

    @ModelAttribute
    public void initForm(Model model){
        model.addAttribute("ListLease",leaseService.listLease());
    }

    @GetMapping("/leasinglist")
    public String leaseList(){
        return "leaselist";
    }
}
