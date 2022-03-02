package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> userList = userService.userList();
        model.addAttribute("listUsers", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        List<Role> roleList = userService.roleList();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        user.setEnable(true);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message",
                "The User Has been Saved Successfully");
        return "redirect:/users";
    }
}
