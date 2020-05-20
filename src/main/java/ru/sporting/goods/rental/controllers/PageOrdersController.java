package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.services.InstanceOfItemService;
import ru.sporting.goods.rental.services.ItemService;
import ru.sporting.goods.rental.services.OrderService;
import ru.sporting.goods.rental.services.UserService;

@Controller
@RequestMapping("/user")
public class PageOrdersController extends BaseController {

    private ItemService itemService;
    private UserService userService;
    private InstanceOfItemService instanceOfItemService;
    private OrderService orderService;

    @GetMapping("/registOrder/{id}")
    public String showPageRegistrOrder(Model model, @ModelAttribute Orders orders,
                                       @PathVariable Long id) {
        model.addAttribute("users", userService.getCurrentUser());
        model.addAttribute("items", itemService.findItemById(id));
        model.addAttribute("instance", instanceOfItemService.findInstancesOfItemByIdItems(id));
        return "forUser/registrationOrder";
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
