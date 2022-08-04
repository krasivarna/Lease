package bg.lease.web;

import bg.lease.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceListController {

    private InvoiceService invoiceService;

    public InvoiceListController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoicelist")
    public String invoiceList(Model model){
        model.addAttribute("invoiceList",invoiceService.listInvoice());
        model.addAttribute("hideCard",true);
        return "invoicelist";
    }
}
