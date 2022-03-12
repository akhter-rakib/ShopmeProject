package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
        model.addAttribute("pageTitle", "Create New User");
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

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            User user = userService.get(id);
            List<Role> roleList = userService.roleList();
            model.addAttribute("roleList", roleList);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID -> "
                    + user.getId() + ") ");
            return "user_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "This user id " + id
                    + "Has Been Deleted Successfully");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
