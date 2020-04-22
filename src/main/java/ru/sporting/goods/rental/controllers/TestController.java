package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.services.TypeOfItemService;

@Controller
public class TestController {

    @Autowired
    private TypeOfItemService typeOfItemService;

    @GetMapping("/tests")
    public String getMainPage(Model model){

        return "mainPage";
    }
}
