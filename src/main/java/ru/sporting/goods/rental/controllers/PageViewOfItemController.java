package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.services.ViewOfItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PageViewOfItemController extends BaseController {

    private ViewOfItemService viewOfItemService;

    //Список всех видов товара
    @GetMapping("/viewOfItem")
    public String getViewOfItem(Model model) {
        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "forAdmin/viewOfItem/viewOfItemList";
    }

    //Переход к определённому виду товара
    @GetMapping("/viewOfItem/{id}")
    public String getViewOfItemById(Model model, @PathVariable Long id) {
        ViewOfItem viewOfItem = null;
        try {
            viewOfItem = viewOfItemService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (RecordNotFound ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("viewOfItem", viewOfItem);
        return "forAdmin/viewOfItem/viewOfItemOne";
    }

    //Добавление нового вида товара
    @GetMapping("/viewOfItem/add")
    public String showAddViewOfItem(Model model) {
        ViewOfItem viewOfItem = null;
        model.addAttribute("add", true);
        model.addAttribute("viewOfItem", viewOfItem);
        return "forAdmin/viewOfItem/viewOfItemEdit";
    }

    //Добавление нового вида товара
    @PostMapping("/viewOfItem/add")
    public String addViewOfItem(Model model,
                                @ModelAttribute("viewOfItem") @Valid ViewOfItem viewOfItem,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("viewOfItem", viewOfItem);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/admin/viewOfItem/add";
        } else {

            ViewOfItem newViewOfItem = viewOfItemService.save(viewOfItem);
            return "redirect:/admin/viewOfItem/" + String.valueOf(newViewOfItem.getId());
        }
    }

    //Редактирование вида товара
    @GetMapping("/viewOfItem/{id}/edit")
    public String showEditViewOfItem(Model model, @PathVariable Long id) {
        ViewOfItem viewOfItem = null;
        try {
            viewOfItem = viewOfItemService.findById(id);
        } catch (RecordNotFound ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("viewOfItem", viewOfItem);
        return "forAdmin/viewOfItem/viewOfItemEdit";
    }

    //Редактирование вида товара
    @PostMapping("/viewOfItem/{id}/edit")
    public String updateViewOfItem(Model model, @PathVariable Long id,
                                   @ModelAttribute("viewOfItem") @Valid ViewOfItem viewOfItem,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("viewOfItem", viewOfItem);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/admin/viewOfItem/" + id + "/edit";
        } else {
            try {
                viewOfItemService.update(viewOfItem);
                return "redirect:/admin/viewOfItem/" + String.valueOf(viewOfItem.getId());
            } catch (RecordNotFound ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/viewOfItem/viewOfItemEdit";
            }
        }
    }

    //Удаление вида товара
    @GetMapping("/viewOfItem/{id}/delete")
    public String showDeleteViewOfItemById(Model model, @PathVariable Long id) {
        ViewOfItem viewOfItem = null;
        try {
            viewOfItem = viewOfItemService.findById(id);
        } catch (RecordNotFound ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("viewOfItem", viewOfItem);
        return "forAdmin/viewOfItem/viewOfItemOne";
    }

    //Удаление вида товара
    @PostMapping("/viewOfItem/{id}/delete")
    public String deleteViewOfItemById(Model model, @PathVariable Long id) {
        try {
            viewOfItemService.deleteById(id);
            return "redirect:/admin/viewOfItem";
        } catch (RecordNotFound ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/viewOfItem/viewOfItemOne";
        }
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService) {
        this.viewOfItemService = viewOfItemService;
    }
}