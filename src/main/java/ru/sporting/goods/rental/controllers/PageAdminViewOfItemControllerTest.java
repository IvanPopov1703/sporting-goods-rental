package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.services.ViewOfItemService;

@Controller
@RequestMapping("/testMap")
public class PageAdminViewOfItemControllerTest {

    private ViewOfItemService viewOfItemService;

    /*@GetMapping("/res")
    public String getRes(Model model){
        model.addAttribute("viewOfItem", viewOfItemService.getAll());
        return "result";
    }

    @GetMapping
    public String getTestEmp(Model model){
        model.addAttribute("testEmp", new ViewOfItem());
        return "viewTest";
    }

    @PostMapping
    public String testEmpSubmit(@ModelAttribute ViewOfItem viewOfItem, Model model){
        model.addAttribute("testEmpty", viewOfItem);
        viewOfItemService.addViewOfItem(viewOfItem);
        return "redirect:/testMap/res";
    }*/

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService){
        this.viewOfItemService = viewOfItemService;
    }
}
