package bg.lease.web;

import bg.lease.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginController {

    private UserService userService;

    public UserLoginController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/users/login")
    public String login(){

        return "login";
    }

    @PostMapping("/users/login-error")
    public String onFailLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                              RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("badcredentials",true);
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,username);
        return "redirect:/users/login";
    }

}
