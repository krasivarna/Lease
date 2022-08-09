package bg.lease.web;

import bg.lease.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PermissionController {

    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/permissionlist")
    public String permissionList(Model model){
        model.addAttribute("permissionList",permissionService.listPermissions());
        model.addAttribute("hideCard",true);
        return "permissionlist";
    }
}
