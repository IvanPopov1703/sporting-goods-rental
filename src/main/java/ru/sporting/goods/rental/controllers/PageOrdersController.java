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

import javax.annotation.security.RolesAllowed;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
public class PageOrdersController extends BaseController {

    private ItemService itemService;
    private UserService userService;
    private InstanceOfItemService instanceOfItemService;
    private OrderService orderService;

    @GetMapping("/user/registOrder/{id}")
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
    @PostMapping("/user/registOrder/add")
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
        return "redirect:/myOrders/" + orders.getUser().getId();
    }

    //Вспомогательная функция для контроллера "Мои заказы"
    private Model helpPageMyOrders(Model model, Long id){
        User user = userService.findById(id);
        model.addAttribute("ordersPending",
                orderService.checkOrdersPending(user));
        model.addAttribute("ordersIssued",
                orderService.checkOrdersIssued(user));
        model.addAttribute("ordersExpired",
                orderService.getOrdersByIdUserAndStatusInstance(user.getId(),
                        InstanceOfItem.STATUS_ORDER_EXPIRED));
        model.addAttribute("ordersHistory",
                orderService.getOrdersByIdUserAndStatusInstance(user.getId(),
                        InstanceOfItem.STATUS_ORDER_HAND_OVER));
        userService.update(user);
        return model;
    }


    //Страница "Мои заказы"
    @RolesAllowed({"ADMIN", "BUYER"})
    @GetMapping("/myOrders/{id}")
    public String getPageMyOrders(Model model, @PathVariable Long id) {
        model = helpPageMyOrders(model, id);
        return "forUser/myOrders";
    }

    //Забрать заказ
    @GetMapping("/user/pickUpOrder/{id}")
    public String pickUpOrder(@PathVariable Long id){
        Orders orders = orderService.findById(id);
        orders.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_ISSUED);
        instanceOfItemService.update(orders.getInstance());
        return "redirect:/myOrders/" + orders.getUser().getId();
    }

    //Вернуть заказ (статус Issued)
    @GetMapping("/user/returnWithIssuedStatus/{id}")
    public String returnGoodsWithIssuedStatus(@PathVariable Long id){
        return "redirect:/myOrders/" + orderService.returnGoodWithIssuedStatus(id);
    }

    //Вернуть заказ (статус Expired)
    @GetMapping("/user/returnWithExpiredStatus/{id}")
    public String returnGoodsWithExpiredStatus(Model model, @PathVariable Long id){
        Orders orders = orderService.findById(id);
        InstanceOfItem instance = instanceOfItemService.findById(orders.getInstance().getId());
        User user = userService.findById(orders.getUser().getId());
        if (orders.getFine() <= (user.getPurse() + Items.AMOUNT_OF_GUARANTEE)){
            //Установка реального времени сдачи
            orders.setRealTimeOfReturningProduct(Date.valueOf(LocalDate.now()));
            //Установка статуса Сдан экземпляру
            instance.setOrder_status(InstanceOfItem.STATUS_ORDER_HAND_OVER);
            user.setPurse((user.getPurse() + Items.AMOUNT_OF_GUARANTEE) - orders.getFine());
            //Обновление данных в таблицах
            orderService.update(orders);
            userService.update(user);
            instanceOfItemService.update(instance);
            return "redirect:/myOrders/" + user.getId();
        }
        model = helpPageMyOrders(model, user.getId());
        model.addAttribute("err", "Для возврата товара и оплаты штрафа, у Вас недостаточно денег! Пополните счёт!");
        return "forUser/myOrders";
    }

    //Открывает старницу заказов со стороны админа
    @GetMapping("/admin/ordersAll/{id}")
    public String getPageMyOrdersAdmin(Model model, @PathVariable Long id) {
        model = helpPageMyOrders(model, id);
        model.addAttribute("admin", "admin");
        return "forUser/myOrders";
    }

    //Переход к выбранному заказу
    @GetMapping("/admin/orders/{id}")
    public String getOrderById(Model model, @PathVariable Long id) {
        Orders orders = null;
        try {
            orders = orderService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("orders", orders);
        return "forAdmin/orders/orderOne";
    }

    //Удаление заказа
    @GetMapping("/admin/orders/{id}/delete")
    public String showDeleteOrderById(Model model, @PathVariable Long id) {
        Orders orders = null;
        try {
            orders = orderService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("orders", orders);
        return "forAdmin/orders/orderOne";
    }

    //Удаление товара
    @PostMapping("/admin/orders/{id}/delete")
    public String deleteOrdersId(Model model, @PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return "redirect:/admin/items";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/orders/orderOne";
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
