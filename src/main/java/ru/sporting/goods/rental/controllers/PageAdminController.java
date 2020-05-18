package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/admin")
public class PageAdminController {

    @RolesAllowed("ADMIN")
    @GetMapping
    public String getTypeOfItemPage(){
        return "forAdmin/allAdmin";
    }
}
