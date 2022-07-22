package bg.lease.web;

import bg.lease.model.dto.CountryDTO;
import bg.lease.model.dto.VendorDTO;
import bg.lease.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CountryCardController {

    private CountryService countryService;

    public CountryCardController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ModelAttribute
    public void initForm(Model model){
        model.addAttribute("countryDTO",new CountryDTO());
    }

    @GetMapping("/countrycard")
    public String countryCard() {
        return "countrycard";
    }

    @PostMapping("/countrycard")
    public String countryCard(@Valid CountryDTO countryDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("countryDTO",countryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.countryDTO",bindingResult);
            return "redirect:/countrycard";
        }
        countryService.addCard(countryDTO);
        return "redirect:/countrylist";
    }

    @GetMapping("/countrycard/{code}")
    public String editCountryCard(Model model, @PathVariable("code") String countryNo){
        CountryDTO country=this.countryService.editCard(countryNo);
        model.addAttribute("countryDTO",country);
        return "countrycard";
    }
}
