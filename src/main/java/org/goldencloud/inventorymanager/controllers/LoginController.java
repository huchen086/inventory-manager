package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.DAO.UserDao;
import org.goldencloud.inventorymanager.DTO.UserDto;
import org.goldencloud.inventorymanager.services.UserService;
import org.goldencloud.inventorymanager.models.User;
import org.goldencloud.inventorymanager.validators.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        model.addAttribute("title", "New User Registration");
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result, Model model, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "New User Registration");
            model.addAttribute("user",accountDto);
            return "register";
        } else {
            createUserAccount(accountDto);
            return "redirect:/login";
        }
    }

    private void createUserAccount(UserDto accountDto) {
        User registered = null;
        try {
            userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {

        }
    }

    @RequestMapping(value = {"", "login"}, method= RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("title","User Login");
        return modelAndView;
    }

    @RequestMapping(value = "home")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to Golden Cloud!");
        return "home";
    }
}
