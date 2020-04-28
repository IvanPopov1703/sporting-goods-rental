package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.TypeOfItemService;

@Controller
@RequestMapping("/admin")
public class PageAdminController {

    private TypeOfItemService typeOfItemService;

    @GetMapping
    public String getTypeOfItemPage(){
        return "forAdmin/addTypeItem";
    }

    @PostMapping
    public String addTypeOfItemPage(@RequestParam String name){
        TypeOfItem typeOfItem = new TypeOfItem(name);
        typeOfItemService.addProductType(typeOfItem);
        return "forAdmin/addTypeItem";
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService){
        this.typeOfItemService = typeOfItemService;
    }
}
