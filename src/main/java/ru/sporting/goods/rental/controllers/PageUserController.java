package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.services.UserService;

@Controller
@RequestMapping("/user")
public class PageUserController extends BaseController {

    private UserService userService;

    //Личный кабинет пользователя
    @GetMapping("/users")
    public String getUser(Model model) {
        User user = null;
        try {
            user = userService.getCurrentUser();
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("users", user);
        return "forUser/userOne";
    }

    //Вспомогательный метод для работы с кошельком
    private Model helpWorkWithWallet(boolean status, Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("up", status);
        model.addAttribute("users", user);
        return model;
    }

    //Пополнение баланса
    @GetMapping("/upPurse")
    public String getUpPurse(Model model) {
        model = helpWorkWithWallet(true, model);
        return "forUser/workWithWallet";
    }

    //Вывод средств
    @GetMapping("/cashWithdrawal")
    public String getCashWithdrawal(Model model) {
        model = helpWorkWithWallet(false, model);
        return "forUser/workWithWallet";
    }

    //Пополнение кошелька
    @PostMapping("/upPurse")
    public String postUpPurse(Model model, @ModelAttribute("users") User user) {
        try {
            User newUser = userService.getCurrentUser();
            newUser.setPurse(user.getPurse() + newUser.getPurse());
            userService.update(newUser);
            return "redirect:/user/users";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("up", true);
            return "forUser/userOne";
        }
    }

    //Списание с кошелька
    @PostMapping("/cashWithdrawal")
    public String postCashWithdrawal(Model model, User user) {
        try {
            User newUser = userService.getCurrentUser();
            if (user.getPurse() > newUser.getPurse()){
                model.addAttribute("up", false);
                model.addAttribute("errorMessage", "Вывод средств невозможен!");
                model.addAttribute("users", newUser);
                return "forUser/workWithWallet";
            }
            newUser.setPurse(newUser.getPurse() - user.getPurse());
            userService.update(newUser);
            return "redirect:/user/users";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("up", false);
            return "forUser/userOne";
        }
    }

    //Страница "Мои заказы"
    @GetMapping("/myOrders")
    public String getPageMyOrders(Model model){
        return "forUser/myOrders";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
