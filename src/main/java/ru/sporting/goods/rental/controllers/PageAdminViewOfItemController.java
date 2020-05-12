package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.services.ViewOfItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/viewOfItem")
public class PageAdminViewOfItemController extends BaseController{

    private ViewOfItemService viewOfItemService;

    @GetMapping("/res")
    public String getRes(Model model){
        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "result";
    }

    @GetMapping
    public String getTestEmp(Model model){
        model.addAttribute("testEmp", new ViewOfItem());
        return "viewTest";
    }

    @PostMapping
    public String testEmpSubmit(@Valid ViewOfItem viewOfItem, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("viewOfItem", viewOfItem);
            addValidationMessage(redirectAttributes, bindingResult);
            return "redirect:/admin/viewOfItem";
        }
        viewOfItemService.addViewOfItem(viewOfItem);
        addSuccessMessage(redirectAttributes, "viewOfItem.created");
        return "redirect:/admin/viewOfItem/res";
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService){
        this.viewOfItemService = viewOfItemService;
    }
}
