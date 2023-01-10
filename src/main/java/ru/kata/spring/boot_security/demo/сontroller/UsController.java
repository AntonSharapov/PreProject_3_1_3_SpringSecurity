//package ru.kata.spring.boot_security.demo.сontroller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class UsController {
//    private UsService usService;
//
//    private User user;
//
//
//    @GetMapping(value = "/")
//    public String welcome() {
//        return "redirect:/users";
//    }
//
//    @GetMapping(value = "users")
//    public String allUsers(ModelMap model) {
//        model.addAttribute("users", usService.getAllUsers());
//        return "users";
//    }
//
//    @GetMapping(value = "users/add")
//    public String addUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "addUser";
//    }
//
//    @PostMapping(value = "users/add")
//    public String addUser(@ModelAttribute("user") User user) {
//        usService.addUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "users/edit/{id}")
//    public String editUser(ModelMap model, @PathVariable("id") Long id) {
//        User user = usService.getUserById(id);
//        model.addAttribute("user", user);
//        return "editUser";
//    }
//
//    @PostMapping(value = "users/edit")
//    public String edit(@ModelAttribute("user") User user) {
//        usService.editUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("users/delete")
//    public String deleteUserById(@RequestParam("id") Long id) {
//        usService.deleteUser(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("users/{id}")
//    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
//        modelMap.addAttribute("user", usService.getUserById(id));
//        return "sh";
//    }
//}
