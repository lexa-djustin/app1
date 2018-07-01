package com.app1.example.Controller;

import com.app1.example.Entity.Profile;
import com.app1.example.Entity.Row;
import com.app1.example.Entity.User;
import com.app1.example.Service.SecurityService;
import com.app1.example.Service.UserService;
import com.app1.example.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(method=RequestMethod.GET)
    public String registration(Model model) {
        System.out.println("GET");

        return "profile/edit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(@ModelAttribute("Profile") Profile profile) {
        System.out.println(profile.getName());

        for (Row row : profile.getRows()) {
            System.out.println(row.getLabel());
        }

        return "profile/edit";
    }
}