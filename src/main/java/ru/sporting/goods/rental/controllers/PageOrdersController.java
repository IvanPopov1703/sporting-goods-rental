package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.services.InstanceOfItemService;
import ru.sporting.goods.rental.services.ItemService;
import ru.sporting.goods.rental.services.OrderService;
import ru.sporting.goods.rental.services.UserService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
        if (orders.getTimeOfReceiptOfItem() != null) {
            model.addAttribute("err", "Дата должна быть будущим временем!");
        }
        Orders order = null;
        Date date2 = Date.valueOf(LocalDate.now()); //Получение текущего времени
        SimpleDateFormat simpDate = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("orders", orders);
        model.addAttribute("currentDate", simpDate.format(date2));
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("items", itemService.findItemById(id));
        model.addAttribute("instance", instanceOfItemService.findInstancesOfItemByIdItems(id));
        return "forUser/registrationOrder";
    }

    //Добавление нового типа товара
    @PostMapping("/registOrder/add")
    public String addOrders(Model model,
                            @ModelAttribute("orders") Orders orders,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        orders.setTimeOfReceiptOfItem(Date.valueOf(LocalDate.now()));
        if ((bindingResult.hasErrors())
                || (!orders.getPlannedTimeOfReturningProduct().after(orders.getTimeOfReceiptOfItem()))
                || (orderService.checkManyForBuy(orders))) {
            redirectAttributes.addFlashAttribute("orders", orders);
            addValidationMessage(redirectAttributes, bindingResult);
            return "redirect:/user/registOrder/" + orders.getInstance().getItems().getId();
        } else {
            if (!orderService.getCountDayRental(orders)){
                redirectAttributes.addFlashAttribute("orders", orders);
                addValidationMessage(redirectAttributes, bindingResult);
                return "redirect:/user/registOrder/" + orders.getInstance().getItems().getId();
            }
            orders.setAmountOfGuarantee(Items.AMOUNT_OF_GUARANTEE);
            orderService.save(orders);
            return "redirect:/user/myOrders";
        }
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

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
