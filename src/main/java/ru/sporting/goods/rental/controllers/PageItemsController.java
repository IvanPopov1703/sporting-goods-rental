package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.services.ItemService;
import ru.sporting.goods.rental.services.TypeOfItemService;
import ru.sporting.goods.rental.services.ViewOfItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PageItemsController extends BaseController {

    private ItemService itemService;
    private TypeOfItemService typeOfItemService;
    private ViewOfItemService viewOfItemService;

    //Список всех типов товара
    @GetMapping("/items")
    public String getTypeOfItem(Model model) {
        model.addAttribute("items", itemService.findAllItemsAndTypeAndView());
        return "forAdmin/items/itemsList";
    }

    //Переход к определённому товару
    @GetMapping("/items/{id}")
    public String getItemById(Model model, @PathVariable Long id) {
        Items items = null;
        try {
            items = itemService.findItemById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("items", items);
        return "forAdmin/items/itemOne";
    }


    //Добавление нового товара
    @GetMapping("/items/add")
    public String showAddItem(Model model, @ModelAttribute("items") Items items) {
        if (items.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("items", items);
        model.addAttribute("add", true);
        model.addAttribute("typeOfItem", typeOfItemService.findAll());
        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "forAdmin/items/itemEdit";
    }

    //Добавление нового типа товара
    @PostMapping("/items/add")
    public String addItem(Model model,
                          @ModelAttribute("items") @Valid Items items,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("items", items);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/admin/items/add";
        } else {
            Items newItem = itemService.save(items);
            return "redirect:/admin/items/" + String.valueOf(newItem.getId());
        }
    }

    //Редактирование товара
    @GetMapping("/items/{id}/edit")
    public String showEditItem(Model model, @PathVariable Long id) {
        Items items = null;
        try {
            items = itemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("items", items);
        model.addAttribute("typeOfItem", typeOfItemService.findAll());
        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "forAdmin/items/itemEdit";
    }

    //Редактирование товара
    @PostMapping("/items/{id}/edit")
    public String updateItem(Model model, @PathVariable Long id,
                             @ModelAttribute("items") @Valid Items items,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("items", items);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/admin/items/" + id + "/edit";
        } else {
            try {
                itemService.update(items);
                return "redirect:/admin/items/" + String.valueOf(items.getId());
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/items/itemEdit";
            }
        }
    }

    //Удаление товара
    @GetMapping("/items/{id}/delete")
    public String showDeleteItemById(Model model, @PathVariable Long id) {
        Items items = null;
        try {
            items = itemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("items", items);
        return "forAdmin/items/itemOne";
    }

    //Удаление товара
    @PostMapping("/items/{id}/delete")
    public String deleteItemsById(Model model, @PathVariable Long id) {
        try {
            itemService.deleteById(id);
            return "redirect:/admin/items";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/items/itemOne";
        }
    }


    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService) {
        this.typeOfItemService = typeOfItemService;
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService) {
        this.viewOfItemService = viewOfItemService;
    }
}
