package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.OrderStatus;
import ru.sporting.goods.rental.services.OrderStatusService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PageStatusOrderController extends BaseController {

    private OrderStatusService orderStatusService;

    //Список всех статусов
    @GetMapping("/statusOrder")
    public String getStatusOrder(Model model) {
        model.addAttribute("statusOrder", orderStatusService.findAll());
        return "forAdmin/statusOrder/statusOrderList";
    }

    //Переход к определённому статусу
    @GetMapping("/statusOrder/{id}")
    public String getOrderStatusById(Model model, @PathVariable Long id) {
        OrderStatus statusOrder = null;
        try {
            statusOrder = orderStatusService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("statusOrder", statusOrder);
        return "forAdmin/statusOrder/statusOrderOne";
    }

    //Добавление нового статуса
    @GetMapping("/statusOrder/add")
    public String showAddOrderStatus(Model model) {
        OrderStatus statusOrder = null;
        model.addAttribute("add", true);
        model.addAttribute("statusOrder", statusOrder);
        return "forAdmin/statusOrder/statusOrderEdit";
    }

    //Добавление нового статуса
    @PostMapping("/statusOrder/add")
    public String addOrderStatus(Model model,
                                 @ModelAttribute("statusOrder") @Valid OrderStatus statusOrder,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("statusOrder", statusOrder);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/admin/statusOrder/add";
        } else {

            OrderStatus newOrderStatus = orderStatusService.save(statusOrder);
            return "redirect:/admin/statusOrder/" + String.valueOf(newOrderStatus.getId());
        }
    }

    //Редактирование статуса
    @GetMapping("/statusOrder/{id}/edit")
    public String showEditOrderStatus(Model model, @PathVariable Long id) {
        OrderStatus statusOrder = null;
        try {
            statusOrder = orderStatusService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("statusOrder", statusOrder);
        return "forAdmin/statusOrder/statusOrderEdit";
    }

    //Редактирование статуса
    @PostMapping("/statusOrder/{id}/edit")
    public String updateOrderStatus(Model model, @PathVariable Long id,
                                    @ModelAttribute("statusOrder") @Valid OrderStatus statusOrder,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("statusOrder", statusOrder);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/admin/statusOrder/" + id + "/edit";
        } else {
            try {
                orderStatusService.update(statusOrder);
                return "redirect:/admin/statusOrder/" + String.valueOf(statusOrder.getId());
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/statusOrder/statusOrderEdit";
            }
        }
    }

    //Удаление статуса
    @GetMapping("/statusOrder/{id}/delete")
    public String showDeleteOrderStatusById(Model model, @PathVariable Long id) {
        OrderStatus statusOrder = null;
        try {
            statusOrder = orderStatusService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("statusOrder", statusOrder);
        return "forAdmin/statusOrder/statusOrderOne";
    }

    //Удаление статуса
    @PostMapping("/statusOrder/{id}/delete")
    public String deleteOrderStatusById(Model model, @PathVariable Long id) {
        try {
            orderStatusService.deleteById(id);
            return "redirect:/admin/statusOrder";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/statusOrder/statusOrderOne";
        }
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }
}
