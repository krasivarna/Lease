package bg.lease.web;

import bg.lease.service.CountryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CountryListController {

    private CountryService countryService;

    public CountryListController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("@globalPermissionService.CountryIsRead(#principal.name)")
    @GetMapping("/countrylist")
    public String countryList(Model model,
                              Principal principal){
        model.addAttribute("countryList",countryService.listCountry());
        model.addAttribute("hideCard",true);
        return "countrylist";
    }
}
