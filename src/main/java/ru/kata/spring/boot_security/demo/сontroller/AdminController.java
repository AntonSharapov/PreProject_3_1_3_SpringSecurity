package ru.kata.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceIMP;

import java.util.stream.Collectors;

@Controller
public class AdminController {
    private UserServiceIMP userServiceIMP;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public AdminController(UserServiceIMP userServiceIMP, BCryptPasswordEncoder passwordEncoder) {
        this.userServiceIMP = userServiceIMP;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping(value = "/")
    public String welcome() {
        return "redirect:/admin";
    }

    @GetMapping("admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userServiceIMP.getAllUsers());
        return "users";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceIMP.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceIMP.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userServiceIMP.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "admin/edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        userServiceIMP.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("OneUser", userServiceIMP.getUserById(userId));
        return "sh";
    }

}



