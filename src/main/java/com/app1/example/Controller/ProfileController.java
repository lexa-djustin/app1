package com.app1.example.Controller;

import com.app1.example.Entity.Profile;
import com.app1.example.Entity.Row;
import com.app1.example.Entity.User;
import com.app1.example.Service.ProfileServiceInterface;
import com.app1.example.Service.SecurityService;
import com.app1.example.Service.UserService;
import com.app1.example.Validator.ProfileValidator;
import com.app1.example.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired(required = true)
    protected ProfileServiceInterface profileService;
    private ObjectError error;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProfileValidator());
    }

    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model) {
        return "profile/list";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") int id) throws JsonProcessingException {
        Profile profile = profileService.get(id);
        ModelAndView model = new ModelAndView("profile/edit");

        model.addObject("id", id);
        model.addObject("name", profile.getName());

        return model;
    }

    @RequestMapping(value = "/load/{id}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String load(@PathVariable(value = "id") int id) throws JsonProcessingException {
        Profile profile = profileService.get(id);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode rowsListNode = mapper.createArrayNode();
        ObjectNode profileNode = mapper.createObjectNode();

        profileNode.put("id", profile.getId());
        profileNode.put("name", profile.getName());

        for (Row row : profile.getRows()) {
            ObjectNode rowNode = mapper.createObjectNode();

            rowNode.put("id", row.getId());
            rowNode.put("label", row.getLabel());
            rowNode.put("type", row.getType());

            rowsListNode.add(rowNode);
        }

        profileNode.put("rows", rowsListNode);

        return profileNode.toString();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("profile/list");

        model.addObject("profiles", profileService.getAll());

        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@ModelAttribute("Profile") @Valid @RequestBody Profile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode profileNode = mapper.createObjectNode();
            profileNode.put("status", "error");

            ArrayNode rowsListNode = mapper.createArrayNode();

            for (ObjectError error : bindingResult.getAllErrors()) {
                ObjectNode rowNode = mapper.createObjectNode();

                rowNode.put(error.getObjectName(), error.getCode());
                rowsListNode.add(rowNode);
            }

            profileNode.put("errors", rowsListNode);

            return profileNode.toString();
        }

        profileService.save(profile);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode profileNode = mapper.createObjectNode();
        profileNode.put("status", "ok");

        return profileNode.toString();
    }

    @RequestMapping(value = "/delete/{id}", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView delete(@PathVariable(value = "id") int id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Profile profile = profileService.get(id);


        System.out.println(profile);


        /*
        if (profile.getId() != id) {
            redirectAttributes.addFlashAttribute("errorMessage", "Profile was not found");
            return new ModelAndView("redirect:/profile/list");
        }
        */

        if (request.getMethod().equals("POST")) {
            profileService.delete(profile);

            redirectAttributes.addFlashAttribute("successMessage", "The form was successfully deleted");
            return new ModelAndView("redirect:/profile/list");
        }

        ModelAndView model = new ModelAndView("profile/delete");

        return model;
    }
}