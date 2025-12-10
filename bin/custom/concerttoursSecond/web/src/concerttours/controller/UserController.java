package concerttours.controller;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserPage(@RequestParam String name, Model model) {
        if(name != null) {
            UserModel userModel = userService.getCurrentUser();
            userModel.setName(name);
            model.addAttribute("user", userModel);
        }

        return "user";
    }
}
