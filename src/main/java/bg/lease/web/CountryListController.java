package bg.lease.web;

import bg.lease.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CountryListController {

    private CountryService countryService;

    public CountryListController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ModelAttribute
    public void initForm(Model model){
        model.addAttribute("countryList",countryService.listCountry());
    }

    @GetMapping("/countrylist")
    public String countryList(){
        return "countrylist";
    }
}
