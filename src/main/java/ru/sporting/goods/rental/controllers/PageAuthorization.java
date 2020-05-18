package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.services.UserService;

import javax.validation.Valid;

@Controller
public class PageAuthorization extends BaseController{

    private UserService userService;

    @GetMapping("/")
    public String test(Model model){
        try {
            User user = userService.getCurrentUser();
            if (user.getRole().equals(User.ROLE_BUYER))
            {
                model.addAttribute("isAut", true);
                model.addAttribute("users", user);
                return "goodsPage";
            } else{
                if (user.getRole().equals(User.ROLE_ADMIN)){
                    return "redirect:/admin";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "authorizationForm";
    }

    @GetMapping("/registr")
    public String getPageRegist(Model model){
        User user = null;
        model.addAttribute("reg", true);
        model.addAttribute("users", user);
        return "forUser/registration";
    }

    //Добавление нового типа товара
    @PostMapping("/registr")
    public String addUser(Model model, @ModelAttribute("users") @Valid User user,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("users", user);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("reg", true);
            return "redirect:/registr";
        }
        else{
            try {
                user.setRole(User.ROLE_BUYER);
                userService.registerUser(user);
                return "redirect:/goodsPage";
            }catch (Exception ex){
                model.addAttribute("errorMessage", ex.getMessage());
                return "redirect:/registr";
            }
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
