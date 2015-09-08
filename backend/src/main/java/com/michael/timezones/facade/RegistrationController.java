package com.michael.timezones.facade;

import com.michael.timezones.model.User;
import com.michael.timezones.repository.UserRepository;
import com.michael.timezones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerSubmit(@ModelAttribute @Valid User user,/* Model model, */BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return  "register";
        }
        if(userService.userExists(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "email", "This email was already registered"));
            return "register";
        }
        userService.addUser(user);
//        model.addAttribute("user", user);
        return "thank_you";
    }
}
