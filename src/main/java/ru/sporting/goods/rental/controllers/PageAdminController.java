package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.services.TypeOfItemService;

@Controller
@RequestMapping("/admin")
public class PageAdminController {

    private TypeOfItemService typeOfItemService;

    @GetMapping
    public String getTypeOfItemPage(){
        return "forAdmin/allAdmin";
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService){
        this.typeOfItemService = typeOfItemService;
    }
}
