package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.entities.OrderStatus;
import ru.sporting.goods.rental.services.OrderStatusService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/status")
public class PageAdminStatusController {

    private OrderStatusService orderStatusService;

    @GetMapping
    public String statusGetAll(Model model){
        model.addAttribute("status", orderStatusService.getAllStatus());
        return "forAdmin/oderStatus/orderStatusAll";
    }

    @GetMapping("/edit")
    public String statusGetEdit(Model model){
        model.addAttribute("orderStatus", new OrderStatus());
        return "forAdmin/oderStatus/orderStatus";
    }

    @GetMapping("/del")
    public String statusGetDel(Model model){
        model.addAttribute("status", orderStatusService.getAllStatus());
        return "forAdmin/oderStatus/orderStatusDel";
    }

    @PostMapping("/edit")
    public String rolePost(@Valid OrderStatus status, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            model.addAttribute("noErrors", true);
            model.addAttribute("orderStatus", status);
            orderStatusService.addStatus(status);
            return "redirect:/admin/status";
        }
        return "forAdmin/oderStatus/orderStatus";
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService){
        this.orderStatusService = orderStatusService;
    }
}
