package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.TypeOfItemService;

@Controller
public class PagesController {

    @Autowired
    TypeOfItemService typeOfItemService;

    //Запуск стартовой страницы
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    //Запуск страницы всех товаров
    @GetMapping("/goodsPage")
    public String goodsPage(Model model){
        model.addAttribute("typeItem", typeOfItemService.getAll());
        return "goods";
    }

    //Запуск страницы одного товара
    @GetMapping("/goodOnePage")
    public String goodsOnePage(){
        return "oneGood";
    }
}
