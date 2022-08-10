package bg.lease.web;

import bg.lease.service.InvoiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class InvoiceListController {

    private InvoiceService invoiceService;

    public InvoiceListController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("@globalPermissionService.InvPayIsRead(#principal.name)")
    @GetMapping("/invoicelist")
    public String invoiceList(Model model, Principal principal){
        model.addAttribute("invoiceList",invoiceService.listInvoice());
        model.addAttribute("hideCard",true);
        return "invoicelist";
    }
}
