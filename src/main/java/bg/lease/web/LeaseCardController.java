package bg.lease.web;

import bg.lease.model.dto.LeaseCardDTO;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.service.LeaseDetailService;
import bg.lease.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LeaseCardController {

    private LeaseService leaseService;
    private LeaseDetailService leaseDetailService;

    public LeaseCardController(LeaseService leaseService,
                               LeaseDetailService leaseDetailService){
        this.leaseService=leaseService;
        this.leaseDetailService = leaseDetailService;
    }

    @GetMapping("/leasecard")
    public String leaseCard(Model model) {
        model.addAttribute("leaseDetails",new LeaseDetailDTO());
        model.addAttribute("leaseCardDTO",new LeaseCardDTO());
        model.addAttribute("hideCard",false);
        return "leaselist";
    }

    @PostMapping("/leasecard")
    public String LeaseCard(@Valid LeaseCardDTO leaseCardDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("leaseCardDTO",leaseCardDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaseCardDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/leasecard";
        }
        try {
            leaseService.addCard(leaseCardDTO);
        } catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("leaseCardDTO",leaseCardDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaseCardDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/leasecard";
        }
        return "redirect:/leasinglist";
    }

    @GetMapping("/leasecard/{code}")
    public String editLeaseCard(Model model, @PathVariable("code") String contractNo){
        LeaseCardDTO contract=this.leaseService.editCard(contractNo);
        model.addAttribute("leaseCardDTO",contract);
        model.addAttribute("leaseDetails",this.leaseDetailService.leaseDetail(contractNo));
        model.addAttribute("hideCard",false);
        return "leaselist";
    }
}
