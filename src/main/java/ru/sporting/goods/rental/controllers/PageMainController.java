package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sporting.goods.rental.entities.*;
import ru.sporting.goods.rental.services.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
public class PageMainController {

    private TypeOfItemService typeOfItemService;
    private ViewOfItemService viewOfItemService;
    private ItemService itemService;
    private InstanceOfItemService instanceOfItemService;
    private UserService userService;

    private Model helpLoadMainPage(Model model){
        model.addAttribute("viewItem", viewOfItemService.findAll());
        model.addAttribute("typeItem", typeOfItemService.findAll());
        return model;
    }

    @GetMapping("/goodsPage")
    public String startMainPage(Model model) {
        model = helpLoadMainPage(model);
        model.addAttribute("items", itemService.findAll());
        return "goods";
    }

    //Запуск страницы одного товара
    @GetMapping("/goodOnePage")
    public String goodsOnePage() {
        return "oneGood";
    }

    //Переход к определённому товару
    @GetMapping("/goodOnePage/items/{id}")
    public String getItemById(Model model, @PathVariable Long id) {
        Items items = null;
        try {
            try {
                User user = userService.getCurrentUser();
                model.addAttribute("autoriz", true);
            } catch (Exception ex) {
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

    @GetMapping("/")
    public String test(Model model) {
        try {
            User user = userService.getCurrentUser();
            if (user.getRole().equals(User.ROLE_BUYER)) {
                model.addAttribute("isAut", true);
                model.addAttribute("users", user);
                return "forward:/goodsPage";
            } else {
                if (user.getRole().equals(User.ROLE_ADMIN))
                    return "redirect:/admin";
            }
        }catch (Exception ex){
            return "redirect:/goodsPage";
        }
        return null;
    }

    //Контроллер сортировки
    @GetMapping("/sort/")
    public String getSortRecord(Model model, @ModelAttribute("typeItem") TypeOfItem typeOfItem,
                                @ModelAttribute("viewItem")ViewOfItem viewOfItem){
        model = helpLoadMainPage(model);
        model.addAttribute("items", itemService.sortItemByIdViewAndType(viewOfItem.getId(), typeOfItem.getId()));
        return "sort";
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService) {
        this.typeOfItemService = typeOfItemService;
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService) {
        this.viewOfItemService = viewOfItemService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setInstanceOfItemService(InstanceOfItemService instanceOfItemService) {
        this.instanceOfItemService = instanceOfItemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
