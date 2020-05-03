package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.ServiceTypeOfItem;

import javax.validation.Valid;

@Controller
public class PageTypeOfItemController {

    private ServiceTypeOfItem serviceTypeOfItem;

    //Загрузка начальной страницы
    @GetMapping("/index")
    public String index() {
        return "forAdmin/typeOfItem/typeOfItem";
    }

    //Список всех типов товара
    @GetMapping("/typeOfItem")
    public String getTypeOfItem(Model model) {
        model.addAttribute("typeOfItem", serviceTypeOfItem.findAll());
        return "forAdmin/typeOfItem/typeOfItemList";
    }

    //Переход к определённому типу товара
    @GetMapping("/typeOfItem/{id}")
    public String getTypeOfItemById(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = serviceTypeOfItem.findById(id);
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
    public String addTypeOfItem(Model model, @ModelAttribute("typeOfItem") @Valid TypeOfItem typeOfItem) {
        try {
            //typeOfItem.setId((long) 15);
            Long k = typeOfItem.getId();
            String n = typeOfItem.getName();
            TypeOfItem newTypeOfItem = serviceTypeOfItem.save(typeOfItem);
            return "redirect:/typeOfItem/" + String.valueOf(newTypeOfItem.getId());
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("add", true);
            return "forAdmin/typeOfItem/typeOfItemEdit";
        }
    }

    //Редактирование типа товара
    @GetMapping("/typeOfItem/{id}/edit")
    public String showEditTypeOfItem(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = serviceTypeOfItem.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemEdit";
    }

    //Редактирование товара
    @PostMapping("/typeOfItem/{id}/edit")
    public String updateTypeOfItem(Model model,
                                   @PathVariable Long id,
                                   @ModelAttribute("typeOfItem") TypeOfItem typeOfItem) {
        try {
            typeOfItem.setId(id);
            serviceTypeOfItem.update(typeOfItem);
            return "redirect:/typeOfItem/" + String.valueOf(typeOfItem.getId());
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("add", false);
            return "forAdmin/typeOfItem/typeOfItemEdit";
        }
    }

    //Удаление товара
    @GetMapping("/typeOfItem/{id}/delete")
    public String showDeleteTypeOfItemById(Model model, @PathVariable Long id) {
        TypeOfItem typeOfItem = null;
        try {
            typeOfItem = serviceTypeOfItem.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("typeOfItem", typeOfItem);
        return "forAdmin/typeOfItem/typeOfItemOne";
    }

    //Удаление товара
    @PostMapping("/typeOfItem/{id}/delete")
    public String deleteTypeOfItemById(Model model, @PathVariable Long id){
        try {
            serviceTypeOfItem.deleteById(id);
            return "redirect:/typeOfItem";
        } catch (Exception ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/typeOfItem/typeOfItemOne";
        }
    }

    @Autowired
    public void setServiceTypeOfItem(ServiceTypeOfItem serviceTypeOfItem) {
        this.serviceTypeOfItem = serviceTypeOfItem;
    }
}
