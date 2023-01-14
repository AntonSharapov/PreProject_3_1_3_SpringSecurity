package ru.kata.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService,BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("admin")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.allUsers());
        return "users";
    }

    @PostMapping("admin/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin/{userId}")
//    public String  getUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergetList(userId));
//        return "sh";
//}
    @GetMapping(value = "admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "admin/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "admin/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.edUser(user);
        return "redirect:/admin";
    }
    @GetMapping("admin/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("OneUser", userService.getUserById(userId));
        return "sh";
    }

}


