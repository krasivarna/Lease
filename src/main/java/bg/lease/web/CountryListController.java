package bg.lease.web;

import bg.lease.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryListController {

    private CountryService countryService;

    public CountryListController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countrylist")
    public String countryList(Model model){
        model.addAttribute("countryList",countryService.listCountry());
        model.addAttribute("hideCard",true);
        return "countrylist";
    }
}
