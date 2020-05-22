package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.services.*;

import javax.annotation.security.RolesAllowed;

@Controller
public class PageMainController {

    private TypeOfItemService typeOfItemService;
    private ViewOfItemService viewOfItemService;
    private ItemService itemService;
    private InstanceOfItemService instanceOfItemService;
    private UserService userService;

    @GetMapping("/goodsPage")
    public String initTypeItem(Model model){
        model.addAttribute("viewItem", viewOfItemService.findAll());
        model.addAttribute("typeItem", typeOfItemService.findAll());
        model.addAttribute("items", itemService.findAll());
        return "goods";
    }

    //Запуск страницы одного товара
    @GetMapping("/goodOnePage")
    public String goodsOnePage(){
        return "oneGood";
    }

    //Переход к определённому товару
    @GetMapping("/goodOnePage/items/{id}")
    public String getItemById(Model model, @PathVariable Long id) {
        Items items = null;
        try {
            try{
                User user = userService.getCurrentUser();
                model.addAttribute("autoriz", true);
            }catch (Exception ex) {
                model.addAttribute("autoriz", false);
            }
            items = itemService.findItemById(id);
            model.addAttribute("numberOfCopies",
                    instanceOfItemService.getNumberOfCopiesByIdItem(InstanceOfItem.STATUS_ORDER_HAND_OVER, id));
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("items", items);
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

    @Autowired
    public void setInstanceOfItemService(InstanceOfItemService instanceOfItemService){
        this.instanceOfItemService = instanceOfItemService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
