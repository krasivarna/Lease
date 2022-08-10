package bg.lease.web;

import bg.lease.model.dto.CountryDTO;
import bg.lease.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CountryCardController {

    private CountryService countryService;

    public CountryCardController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("@globalPermissionService.CountryIsInsert(#principal.name)")
    @GetMapping("/countrycard")
    public String countryCard(Model model,
                              Principal principal) {
        model.addAttribute("countryDTO",new CountryDTO());
        model.addAttribute("hideCard",false);
        return "countrylist";
    }

    @PreAuthorize("@globalPermissionService.CountryIsInsert(#principal.name)")
    @PostMapping("/countrycard")
    public String countryCard(@Valid CountryDTO countryDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                              Principal principal){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("countryDTO",countryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.countryDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/countrycard";
        }
        countryService.addCard(countryDTO);
        return "redirect:/countrylist";
    }

    @PreAuthorize("@globalPermissionService.CountryIsRead(#principal.name)")
    @GetMapping("/countrycard/{code}")
    public String editCountryCard(Model model,
                                  @PathVariable("code") String countryNo,
                                  Principal principal){
        CountryDTO country=this.countryService.editCard(countryNo);
        model.addAttribute("countryDTO",country);
        model.addAttribute("hideCard",false);
        return "countrylist";
    }

    @PreAuthorize("@globalPermissionService.CountryIsDelete(#principal.name)")
    @GetMapping("/deletecountrycard/{code}")
    public String deleteCountryCard(Model model,
                                    @PathVariable("code") String countryNo,
                                    Principal principal){
        model.addAttribute("hideCard",false);
        this.countryService.deleteCard(countryNo);
        return "redirect:/countrylist";
    }
}
