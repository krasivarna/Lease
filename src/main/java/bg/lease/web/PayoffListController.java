package bg.lease.web;

import bg.lease.service.PayoffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PayoffListController {

    private PayoffService payoffService;

    public PayoffListController(PayoffService payoffService) {
        this.payoffService = payoffService;
    }

    @GetMapping("/payofflist/{code}/{lineno}")
    public String payOffList(Model model,
                             @PathVariable("code") String contractNo,
                             @PathVariable("lineno") int lineNo){
        model.addAttribute("ListPay",this.payoffService.payoffList(contractNo, lineNo));
        model.addAttribute("contractNo",contractNo);
        model.addAttribute("showPay",true);
        model.addAttribute("readonly",true);
        return "payofflist";
    }
}
