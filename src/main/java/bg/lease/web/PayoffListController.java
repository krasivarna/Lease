package bg.lease.web;

import bg.lease.model.dto.PayoffListDTO;
import bg.lease.service.PayoffService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreInvocationAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PayoffListController {

    private PayoffService payoffService;

    public PayoffListController(PayoffService payoffService) {
        this.payoffService = payoffService;
    }

    @PreAuthorize("@globalPermissionService.PayoffIsRead(#principal.name)")
    @GetMapping("/payofflist/{code}/{lineno}")
    public String payOffList(Model model,
                             @PathVariable("code") String contractNo,
                             @PathVariable("lineno") int lineNo,
                             Principal principal){
        List<PayoffListDTO> results=this.payoffService.payoffList(contractNo, lineNo);
        model.addAttribute("ListPay",results);
        model.addAttribute("contractNo",contractNo);
        model.addAttribute("showPay",true);
        model.addAttribute("readonly",true);
        model.addAttribute("hasError",results.size()==0);
        model.addAttribute("listErrors",new ArrayList<String>(Arrays.asList("No data for this filter!")));
        return "payofflist";
    }
}
