package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.entities.User;
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
    public String showPageRegistrOrder(Model model, @ModelAttribute("orders") Orders orders,
                                       @PathVariable Long id) {
        if (orders.getTimeOfReceiptOfItem() != null) {
            model.addAttribute("err",
                    "Ошибка оформления заказа! Некорректная дата или недостатлчно средств!");
        }
        Date date2 = Date.valueOf(LocalDate.now()); //Получение текущего времени
        SimpleDateFormat simpDate = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("orders", orders);
        model.addAttribute("currentDate", simpDate.format(date2));
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("items", itemService.findItemById(id));
        model.addAttribute("instance",
                instanceOfItemService.getListOfCopiesByIdItemAndStatus(id, InstanceOfItem.STATUS_ORDER_HAND_OVER));
        return "forUser/registrationOrder";
    }

    //Добавление нового заказа
    @PostMapping("/registOrder/add")
    public String addOrders(Model model,
                            @ModelAttribute("orders") Orders orders,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        orders.setTimeOfReceiptOfItem(Date.valueOf(LocalDate.now()));
        if ((bindingResult.hasErrors())
                || (!orders.getPlannedTimeOfReturningProduct().after(orders.getTimeOfReceiptOfItem()))
                || (!orderService.getCountDayRental(orders))) {
            redirectAttributes.addFlashAttribute("orders", orders);
            addValidationMessage(redirectAttributes, bindingResult);
            return "redirect:/user/registOrder/" + orders.getInstance().getItems().getId();
        }
        orders.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_PENDING);
        orders.setAmountOfGuarantee(Items.AMOUNT_OF_GUARANTEE);
        orderService.save(orders);
        return "redirect:/user/myOrders";
    }

    //Страница "Мои заказы"
    @GetMapping("/myOrders/{id}")
    public String getPageMyOrders(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("ordersPending",
                orderService.checkOrdersPending(user));
        model.addAttribute("ordersIssued",
                orderService.checkOrdersIssued(user));
        model.addAttribute("ordersExpired",
                orderService.getOrdersByIdUserAndStatusInstance(userService.getCurrentUser().getId(),
                        InstanceOfItem.STATUS_ORDER_EXPIRED));
        userService.update(user);
        return "forUser/myOrders";
    }

    //Забрать заказ
    @GetMapping("/pickUpOrder/{id}")
    public String pickUpOrder(@PathVariable Long id){
        Orders orders = orderService.findById(id);
        orders.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_ISSUED);
        instanceOfItemService.update(orders.getInstance());
        return "redirect:/user/myOrders/" + orders.getUser().getId();
    }

    //Вернуть заказ (статус Issued)
    @GetMapping("/returnWithIssuedStatus/{id}")
    public String returnGoodsWithIssuedStatus(@PathVariable Long id){
        Orders orders = orderService.findById(id);
        InstanceOfItem instance = instanceOfItemService.findById(orders.getInstance().getId());
        User user = userService.findById(orders.getUser().getId());
        //Установка реального времени сдачи
        orders.setRealTimeOfReturningProduct(Date.valueOf(LocalDate.now()));
        //Установка статуса Сдан экземпляру
        instance.setOrder_status(InstanceOfItem.STATUS_ORDER_HAND_OVER);
        //Возмещение пользователю денежных средств
        user.setPurse(user.getPurse() +
                (orderService.getCountDayByDate(Date.valueOf(LocalDate.now()),
                orders.getPlannedTimeOfReturningProduct()) *
                        instance.getItems().getСostOneDayRental())
                + Items.AMOUNT_OF_GUARANTEE);
        //Добавление к экземпляру часов пользования
        instance.setHoursOfUse(instance.getHoursOfUse() + orderService.getCountDayByDate(orders.getTimeOfReceiptOfItem(),
                Date.valueOf(LocalDate.now())) * InstanceOfItem.DAY_RENTAL);
        //Обновление данных в таблицах
        orderService.update(orders);
        userService.update(user);
        instanceOfItemService.update(instance);
        return "redirect:/user/myOrders/" + user.getId();
    }

    //Вернуть заказ (статус Expired)
    @GetMapping("/returnWithExpiredStatus/{id}")
    public String returnGoodsWithExpiredStatus(@PathVariable Long id){
        return null;
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
