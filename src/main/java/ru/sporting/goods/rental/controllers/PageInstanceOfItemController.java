package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.services.InstanceOfItemService;
import ru.sporting.goods.rental.services.ItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PageInstanceOfItemController extends BaseController {

    private InstanceOfItemService instanceOfItemService;
    private ItemService itemService;

    //Список всех экземпляров товара
    @GetMapping("/instance")
    public String getTypeOfItem(Model model) {
        model.addAttribute("instance", instanceOfItemService.findAllInstanceAndItemsAndTypeAndView());
        return "forAdmin/instanceOfItem/instanceList";
    }

    //Переход к экземпляру товара
    @GetMapping("/instance/{id}")
    public String getInstanceById(Model model, @PathVariable Long id) {
        InstanceOfItem instance = null;
        try {
            instance = instanceOfItemService.findInstanceOfItemById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("instance", instance);
        return "forAdmin/instanceOfItem/instanceOne";
    }


    //Добавление нового экземпляра товара
    @GetMapping("/instance/add")
    public String showAddInstanceOfItem(Model model, @ModelAttribute InstanceOfItem instance) {
        if (instance.getPurchasePrice() == 0) {
            InstanceOfItem instances = null;
            model.addAttribute("instance", instances);
        } else {
            model.addAttribute("instance", instance);
            model.addAttribute("err", true);
        }
        model.addAttribute("add", true);
        model.addAttribute("items", itemService.findAllItemsAndTypeAndView());
//        model.addAttribute("typeOfItem", typeOfItemService.findAll());
//        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "forAdmin/instanceOfItem/instanceEdit";
    }

    //Добавление нового экземпляра товара
    @PostMapping("/instance/add")
    public String addInstanceOfItem(Model model,
                                    @ModelAttribute("instance") @Valid InstanceOfItem instance,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("instance", instance);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/admin/instance/add";
        } else {
            InstanceOfItem newInstance = instanceOfItemService.save(instance);
            return "redirect:/admin/instance/" + String.valueOf(newInstance.getId());
        }
    }

    //Редактирование экземпляра товара
    @GetMapping("/instance/{id}/edit")
    public String showEditInstanceOfItem(Model model, @PathVariable Long id) {
        InstanceOfItem instance = null;
        try {
            instance = instanceOfItemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("instance", instance);
        model.addAttribute("items", itemService.findAllItemsAndTypeAndView());
//        model.addAttribute("typeOfItem", typeOfItemService.findAll());
//        model.addAttribute("viewOfItem", viewOfItemService.findAll());
        return "forAdmin/instanceOfItem/instanceEdit";
    }

    //Редактирование экземпляра товара
    @PostMapping("/instance/{id}/edit")
    public String updateInstanceOfItem(Model model, @PathVariable Long id,
                                       @ModelAttribute("instance") @Valid InstanceOfItem instance,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("instance", instance);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/admin/instance/" + id + "/edit";
        } else {
            try {
                instanceOfItemService.update(instance);
                return "redirect:/admin/instance/" + String.valueOf(instance.getId());
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/instanceOfItem/instanceEdit";
            }
        }
    }

    //Удаление товара
    @GetMapping("/instance/{id}/delete")
    public String showDeleteInstanceOfItemById(Model model, @PathVariable Long id) {
        InstanceOfItem instance = null;
        try {
            instance = instanceOfItemService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("instance", instance);
        return "forAdmin/instanceOfItem/instanceOne";
    }

    //Удаление товара
    @PostMapping("/instance/{id}/delete")
    public String deleteInstanceOfItemById(Model model, @PathVariable Long id) {
        try {
            instanceOfItemService.deleteById(id);
            return "redirect:/admin/instance";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/instanceOfItem/instanceOne";
        }
    }

    @Autowired
    public void setInstanceOfItemService(InstanceOfItemService instanceOfItemService) {
        this.instanceOfItemService = instanceOfItemService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
