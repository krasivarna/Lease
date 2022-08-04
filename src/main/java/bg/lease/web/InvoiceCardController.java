package bg.lease.web;

import bg.lease.model.dto.InvoiceDTO;
import bg.lease.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class InvoiceCardController {

    private InvoiceService invoiceService;

    public InvoiceCardController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoicecard")
    public String invoiceCard(Model model) {
        model.addAttribute("invoiceDTO",new InvoiceDTO());
        model.addAttribute("hideCard",false);
        return "invoicelist";
    }

    @PostMapping("/invoicecard")
    public String invoiceCard(@Valid InvoiceDTO invoiceDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("invoiceDTO",invoiceDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.invoiceDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/invoicecard";
        }
        invoiceService.addCard(invoiceDTO);
        return "redirect:/invoicelist";
    }
}
