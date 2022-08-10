package bg.lease.web;

import bg.lease.service.UserPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserPermissionController {

    private UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @PreAuthorize("@globalPermissionService.UserPermissionIsRead(#principal.name)")
    @GetMapping("/userpermissionlist")
    public String userPermissionList(Model model, Principal principal){
        model.addAttribute("userpermissionList",userPermissionService.listPermissions());
        model.addAttribute("hideCard",true);
        return "userpermissionlist";
    }
}
