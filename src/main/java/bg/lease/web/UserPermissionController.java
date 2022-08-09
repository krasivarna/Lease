package bg.lease.web;

import bg.lease.service.UserPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPermissionController {

    private UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @GetMapping("/userpermissionlist")
    public String userPermissionList(Model model){
        model.addAttribute("userpermissionList",userPermissionService.listPermissions());
        model.addAttribute("hideCard",true);
        return "userpermissionlist";
    }
}
