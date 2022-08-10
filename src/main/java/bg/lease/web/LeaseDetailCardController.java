package bg.lease.web;

import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.service.LeaseDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LeaseDetailCardController {

    private LeaseDetailService leaseDetailService;

    public LeaseDetailCardController(LeaseDetailService leaseDetailService) {
        this.leaseDetailService = leaseDetailService;
    }

    @PreAuthorize("@globalPermissionService.LeaseDetailIsRead(#principal.name)")
    @GetMapping("/leasedetailcard/{code}/{lineno}")
    public String editLeaseDetailCard(Model model,
                                      @PathVariable("code") String contractNo,
                                      @PathVariable("lineno") int lineNo){
        model.addAttribute("leaseDetailDTO",this.leaseDetailService.editLeaseDetailCard(contractNo, lineNo));
        model.addAttribute("showList",false);
        model.addAttribute("showCard",false);
        model.addAttribute("showDetailCard",true);
        return "leaselist";
    }

    @PreAuthorize("@globalPermissionService.LeaseDetailIsInsert(#principal.name)")
    @PostMapping("/leasedetailcard")
    public String LeaseCard(@Valid LeaseDetailDTO leaseDetailDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("leaseDetailDTO",leaseDetailDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaseDetailDTO",bindingResult);
            redirectAttributes.addFlashAttribute("showList",false);
            redirectAttributes.addFlashAttribute("showCard",false);
            redirectAttributes.addFlashAttribute("showDetailCard",true);
            return "redirect:/leasedetailcard";
        }
        try {
            leaseDetailService.addDetailCard(leaseDetailDTO);
        } catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("leaseCardDTO",leaseDetailDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaseDetailDTO",bindingResult);
            redirectAttributes.addFlashAttribute("showList",false);
            redirectAttributes.addFlashAttribute("showCard",false);
            redirectAttributes.addFlashAttribute("showDetailCard",true);
            return "redirect:/leasedetailcard";
        }
        return "redirect:/leasecard/"+leaseDetailDTO.getContractNo();
    }

    @PreAuthorize("@globalPermissionService.LeaseDetailIsDelete(#principal.name)")
    @GetMapping("/deleteleasedetailcard/{code}/{lineno}")
    public String deleteLeaseDeatilCard(Model model,
                                        @PathVariable("code") String contractNo,
                                        @PathVariable("lineno") int lineNo,
                                        Principal principal){
        model.addAttribute("hideCard",false);
        this.leaseDetailService.deleteCard(contractNo,lineNo);
        return "redirect:/leasecard/"+contractNo;
    }
}
