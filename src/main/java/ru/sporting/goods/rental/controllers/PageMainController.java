package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.services.ItemService;
import ru.sporting.goods.rental.services.TypeOfItemService;
import ru.sporting.goods.rental.services.ViewOfItemService;

import javax.annotation.security.RolesAllowed;

@Controller
public class PageMainController {

    private TypeOfItemService typeOfItemService;
    private ViewOfItemService viewOfItemService;
    private ItemService itemService;

    @GetMapping("/goodsPage")
    public String initTypeItem(Model modelTypeItem, Model modelViewItem, Model modelItem){
        //Подгрузка на страницу видов товаров
        modelViewItem.addAttribute("viewItem", viewOfItemService.findAll());
        //Подгрузка на страницу типов товаров
        modelTypeItem.addAttribute("typeItem", typeOfItemService.findAll());
        //Подгрузка на страницу экземпляров товара
        modelItem.addAttribute("itemPage", itemService.findAll());
        return "goods";
    }

    //Запуск страницы одного товара
    @GetMapping("/goodOnePage")
    public String goodsOnePage(){
        return "oneGood";
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService){
        this.typeOfItemService = typeOfItemService;
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService){
        this.viewOfItemService = viewOfItemService;
    }

    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    }
}
