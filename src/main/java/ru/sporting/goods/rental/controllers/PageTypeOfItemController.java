package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.TypeOfItemService;

import javax.validation.Valid;
import java.lang.reflect.Type;

@Controller
@RequestMapping("/admin")
public class PageTypeOfItemController extends BaseController {

    private TypeOfItemService typeOfItemService;

    //Список всех типов товара
    @GetMapping("/typeOfItem")
    public String getTypeOfItem(Model model) {
        model.addAttribute("typeOfItem", typeOfItemService.findAll());
        return "forAdmin/typeOfItem/typeOfItemList";
    }

    //Переход к определённому типу товара
    @GetMapping("/typeOfItem/{id}")
    public String getTypeOfItemById(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = typeOfItemService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemOne";
    }

    //Добавление нового типа товара
    @GetMapping("/typeOfItem/add")
    public String showAddTypeOfItem(Model model) {
        TypeOfItem typeOfItem = null;
        model.addAttribute("add", true);
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemEdit";
    }

    //Добавление нового типа товара
    @PostMapping("/typeOfItem/add")
    public String addTypeOfItem(Model model,
                                @ModelAttribute("typeOfItem") @Valid TypeOfItem typeOfItem,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("typeOfItem", typeOfItem);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/admin/typeOfItem/add";
        }
        TypeOfItem newTypeOfItem = typeOfItemService.save(typeOfItem);
        return "redirect:/admin/typeOfItem/" + String.valueOf(newTypeOfItem.getId());
    }

    //Редактирование типа товара
    @GetMapping("/typeOfItem/{id}/edit")
    public String showEditTypeOfItem(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = typeOfItemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemEdit";
    }

    //Редактирование товара
    @PostMapping("/typeOfItem/{id}/edit")
    public String updateTypeOfItem(Model model, @PathVariable Long id,
                                   @ModelAttribute("typeOfItem") @Valid TypeOfItem typeOfItem,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("typeOfItem", typeOfItem);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/admin/typeOfItem/" + id + "/edit";
        } else {
            try {
                typeOfItemService.update(typeOfItem);
                return "redirect:/admin/typeOfItem/" + String.valueOf(typeOfItem.getId());
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/typeOfItem/typeOfItemEdit";
            }
        }
    }

    //Удаление товара
    @GetMapping("/typeOfItem/{id}/delete")
    public String showDeleteTypeOfItemById(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = typeOfItemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemOne";
    }

    //Удаление товара
    @PostMapping("/typeOfItem/{id}/delete")
    public String deleteTypeOfItemById(Model model, @PathVariable Long id) {
        try {
            typeOfItemService.deleteById(id);
            return "redirect:/admin/typeOfItem";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/typeOfItem/typeOfItemOne";
        }
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService) {
        this.typeOfItemService = typeOfItemService;
    }
}