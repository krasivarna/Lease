package bg.lease.web;

import bg.lease.model.dto.LeaseCardDTO;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.model.exceptions.WrongLeaseStatusException;
import bg.lease.service.LeaseDetailService;
import bg.lease.service.LeaseService;
import bg.lease.service.PayOffGenerateService;
import bg.lease.util.TransformErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class LeaseCardController {

    private LeaseService leaseService;
    private LeaseDetailService leaseDetailService;
    private PayOffGenerateService payOffGenerateService;
    private TransformErrors transformErrors;

    public LeaseCardController(LeaseService leaseService,
                               LeaseDetailService leaseDetailService,
                               PayOffGenerateService payOffGenerateService,
                               TransformErrors transformErrors){
        this.leaseService=leaseService;
        this.leaseDetailService = leaseDetailService;
        this.payOffGenerateService = payOffGenerateService;
        this.transformErrors = transformErrors;
    }

    @GetMapping("/leasecard")
    public String leaseCard(Model model) {
        model.addAttribute("leaseDetails",new LeaseDetailDTO());
        model.addAttribute("leaseCardDTO",new LeaseCardDTO());
        setModelAttribute(model, false, true, false);
        return "leaselist";
    }

    @GetMapping("/leasecard/{code}/generateplan")
    public String generatePayoffplan(Model model, @PathVariable("code") String contractNo){
        try{
            payOffGenerateService.generatePayoffPlan(contractNo);
        } catch (RuntimeException e)
        {
            model.addAttribute("listErrors",new ArrayList<String>(Arrays.asList(e.getMessage())));
            model.addAttribute("hasError",true);
        }
        refreshLeaseCard(contractNo, model);
        return "leaselist";
    }

    @GetMapping("/leasecard/{code}/deleteplan")
    public String deletePayoffplan(Model model,@PathVariable("code") String contractNo)
    {
        payOffGenerateService.deletePayoffPlan(contractNo);
        refreshLeaseCard(contractNo, model);
        return "leaselist";
    }

    @PostMapping("/leasecard")
    public String LeaseCard(@Valid LeaseCardDTO leaseCardDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            updateLeaseCard(redirectAttributes,bindingResult,leaseCardDTO);
            return "redirect:/leasecard";
        }
        try {
            leaseService.addCard(leaseCardDTO);
        } catch (RuntimeException e){
            bindingResult.addError(new FieldError("","",e.getMessage()));
            updateLeaseCard(redirectAttributes,bindingResult,leaseCardDTO);
            return "redirect:/leasecard";
        }
        return "redirect:/leasinglist";
    }

    private void updateLeaseCard(RedirectAttributes redirectAttributes,
                                 BindingResult bindingResult,
                                 LeaseCardDTO leaseCardDTO){
        redirectAttributes.addFlashAttribute("leaseCardDTO",leaseCardDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaseCardDTO",bindingResult);
        redirectAttributes.addFlashAttribute("showList",false);
        redirectAttributes.addFlashAttribute("showCard",true);
        redirectAttributes.addFlashAttribute("showDetailCard",false);
        redirectAttributes.addFlashAttribute("listErrors",transformErrors.listOfErrors(bindingResult.getFieldErrors()));
        redirectAttributes.addFlashAttribute("hasError",true);
    }

    @GetMapping("/leasecard/{code}")
    public String editLeaseCard(Model model, @PathVariable("code") String contractNo){
        refreshLeaseCard(contractNo, model);
        return "leaselist";
    }

    @GetMapping("/deleteleasecard/{code}")
    public String deleteLeaseCard(@PathVariable("code") String contractNo,
                                  Model model) throws WrongLeaseStatusException {
        try{
            this.leaseService.deleteCard(contractNo);
        } catch (WrongLeaseStatusException e) {
            model.addAttribute("hasError",true);
            model.addAttribute("deleteError",e.getMessage());
            setModelAttribute(model, true, false, false);
        }
        return "leaselist";
    }

    private void setModelAttribute(Model model, boolean showList, boolean showCard, boolean showDetail) {
        model.addAttribute("showList", showList);
        model.addAttribute("showCard", showCard);
        model.addAttribute("showDetailCard", showDetail);
    }

    private void setLeaseDTOs(Model model, LeaseCardDTO contract, String contractNo) {
        model.addAttribute("leaseCardDTO", contract);
        model.addAttribute("leaseDetails",this.leaseDetailService.leaseDetail(contractNo));
    }

    private void refreshLeaseCard(String contractNo, Model model) {
        LeaseCardDTO contract=this.leaseService.editCard(contractNo);
        setLeaseDTOs(model, contract, contractNo);
        setModelAttribute(model, false, true, false);
    }

}
