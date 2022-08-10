package bg.lease.web;

import bg.lease.model.dto.UserPermissionDTO;
import bg.lease.service.UserPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserPermissionCardController {

    private UserPermissionService userPermissionService;

    public UserPermissionCardController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @PreAuthorize("@globalPermissionService.UserPermissionIsRead(#principal.name)")
    @GetMapping("/userpermissioncard")
    public String userPermissionCard(Model model, Principal principal) {
        model.addAttribute("userPermissionDTO",new UserPermissionDTO());
        model.addAttribute("hideCard",false);
        return "userpermissionlist";
    }

    @PreAuthorize("@globalPermissionService.UserPermissionIsInsert(#principal.name)")
    @PostMapping("/userpermissioncard")
    public String countryCard(@Valid UserPermissionDTO userPermissionDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userPermissionDTO",userPermissionDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userPermissionDTO",bindingResult);
            redirectAttributes.addFlashAttribute("hideCard",false);
            return "redirect:/userpermissioncard";
        }
        userPermissionService.addCard(userPermissionDTO);
        return "redirect:/userpermissionlist";
    }

    @PreAuthorize("@globalPermissionService.UserPermissionIsRead(#principal.name)")
    @GetMapping("/userpermissioncard/{id}")
    public String editUserPermissionCard(Model model, @PathVariable("id") Integer id,
                                         Principal principal){
        UserPermissionDTO userpermission=this.userPermissionService.editCard(id);
        model.addAttribute("userPermissionDTO",userpermission);
        model.addAttribute("hideCard",false);
        return "userpermissionlist";
    }

    @PreAuthorize("@globalPermissionService.UserPermissionIsDelete(#principal.name)")
    @GetMapping("/deleteuserpermissioncard/{id}")
    public String deleteUserPermissionCard(Model model, @PathVariable("id") Integer id,
                                           Principal principal){
        model.addAttribute("hideCard",false);
        this.userPermissionService.deleteCard(id);
        return "redirect:/userpermissionlist";
    }
}
