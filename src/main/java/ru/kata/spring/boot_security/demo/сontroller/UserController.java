package ru.kata.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.service.UserServiceIMP;

import java.security.Principal;

@Controller
public class UserController {
    private UserServiceIMP userServiceIMP;
    @Autowired
    public UserController(UserServiceIMP userServiceIMP) {
        this.userServiceIMP = userServiceIMP;
    }
    @GetMapping ("user")
    public String oneUser(Model model, Principal principal) {
        model.addAttribute("oneUser", userServiceIMP.getByUsername(principal.getName()));
        return "sh";
    }
}
