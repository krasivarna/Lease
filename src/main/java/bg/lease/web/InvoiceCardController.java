package bg.lease.web;

import bg.lease.model.dto.InvoiceDTO;
import bg.lease.model.dto.LeaseCardDTO;
import bg.lease.service.InvoiceService;
import bg.lease.util.TransformErrors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class InvoiceCardController {

    private InvoiceService invoiceService;
    private TransformErrors transformErrors;

    public InvoiceCardController(InvoiceService invoiceService,
                                 TransformErrors transformErrors) {
        this.invoiceService = invoiceService;
        this.transformErrors = transformErrors;
    }

    @PreAuthorize("@globalPermissionService.InvPayIsInsert(#principal.name)")
    @GetMapping("/invoicecard")
    public String invoiceCard(Model model, Principal principal) {
        model.addAttribute("invoiceDTO",new InvoiceDTO());
        model.addAttribute("hideCard",false);
        return "invoicelist";
    }

    @PreAuthorize("@globalPermissionService.InvPayIsInsert(#principal.name)")
    @PostMapping("/invoicecard")
    public String invoiceCard(@Valid InvoiceDTO invoiceDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal){
        if (bindingResult.hasErrors()){
            updateInvoiceCard(redirectAttributes,bindingResult,invoiceDTO);
            return "redirect:/invoicecard";
        }
        try {
            invoiceService.addCard(invoiceDTO);
        } catch (RuntimeException e){
            bindingResult.addError(new FieldError("","",e.getMessage()));
            updateInvoiceCard(redirectAttributes,bindingResult,invoiceDTO);
            return "redirect:/invoicecard";
        }
        return "redirect:/invoicelist";
    }

    private void updateInvoiceCard(RedirectAttributes redirectAttributes,
                                 BindingResult bindingResult,
                                   InvoiceDTO invoiceDTO){
        redirectAttributes.addFlashAttribute("invoiceDTO",invoiceDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.invoiceDTO",bindingResult);
        redirectAttributes.addFlashAttribute("hideCard",false);
        redirectAttributes.addFlashAttribute("listErrors",transformErrors.listOfErrors(bindingResult.getFieldErrors()));
        redirectAttributes.addFlashAttribute("hasError",true);
    }
}
