package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.services.UserService;

@Controller
public class PageAuthorization {

    private UserService userService;

    @GetMapping("/authoriz")
    public String showAuthoriz(){
        return "authorizationForm";
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
