package com.company.store.controller;

import com.company.store.entities.User;
import com.company.store.repository.UserDAO;
import com.company.store.validators.SignUpValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
public class UserController {

    private UserDAO userDAO;
    private final SignUpValidator signUpValidator;

    @Autowired
    public UserController(UserDAO userDAO, SignUpValidator signUpValidator) {
        this.userDAO = userDAO;
        this.signUpValidator = signUpValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(signUpValidator);
    }

    @RequestMapping(value = "/signup")
    public String showRegistrationForm(Map<String, Object> model) {
        model.put("registrationForm", User.newBuilder());
        return "registration";
    }

    @RequestMapping(value="addUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("registrationForm")  User user,
                           BindingResult result,
                           @RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("address") String address) {
        if (result.hasErrors())
            return "registration";
        else {
            user = User.newBuilder()
                    .setName(name)
                    .setSurname(surname)
                    .setEmail(email)
                    .setPhone(phone)
                    .setPassword(password)
                    .setAddress(address)
                    .build();
            if (userDAO.saveUser(user))
                return "redirect:/";
            else
                return "registration";
        }
    }

    @RequestMapping(value = "allusers")
    public ModelAndView getAllUsers() {
        Collection<User> users = userDAO.getAllUsers();
        return new ModelAndView("allusers", "users", users);

    }

    @RequestMapping(value = "getById")
    public ModelAndView getById(@RequestParam(value = "id") int id) {
        User user = userDAO.getById(id);
        return new ModelAndView("getById", "user", user);
    }

    @RequestMapping(value = "removeUser")
    public ModelAndView removeUser(@RequestParam(value = "id") int id) {
        Boolean up = userDAO.removeUser(id);
        if (up)
            return new ModelAndView("removeUser");
        else
            return new ModelAndView("user");
    }
}
