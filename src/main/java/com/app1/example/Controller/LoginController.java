package com.app1.example.Controller;

import com.app1.example.Entity.User;
import com.app1.example.Service.SecurityService;
import com.app1.example.Service.UserService;
import com.app1.example.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Security;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "auth/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("UserForm") User userForm, BindingResult bindingResult, Model model) {
        this.userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        this.userService.save(userForm);
        this.securityService.autoLogin(userForm.getLogin(), userForm.getPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Login or password incorrect");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }

        return "auth/login";
    }

    @RequestMapping(value = {"/", "/welcome"})
    public String Welcome() {
        return "index/welcome";
    }

    @RequestMapping(value = {"/admin"})
    public String admin(Model model) {
        return "admin";
    }
}