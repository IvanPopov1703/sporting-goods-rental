package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.entities.Role;
import ru.sporting.goods.rental.services.RoleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminTest/role")
public class PageAdminRoleController {

    private RoleService roleService;

    @GetMapping
    public String roleGet(Model model) {
        model.addAttribute("role", new Role());
        return "orderStatus";
    }

    @PostMapping
    public String rolePost(@Valid Role role, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noErrors", true);
        }
        model.addAttribute("role", role);
        return "orderStatus";
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
