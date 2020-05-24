package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class PageUserController extends BaseController {

    private UserService userService;

    //Переход к главной странице админа
    @GetMapping("/admin")
    public String getAdminPage() {
        return "forAdmin/allAdmin";
    }

    @GetMapping("/")
    public String test(Model model) {
        User user = userService.getCurrentUser();
        if (user.getRole().equals(User.ROLE_BUYER)) {
            model.addAttribute("isAut", true);
            model.addAttribute("users", user);
            return "goodsPage";
        } else {
            return "redirect:/admin";
        }
    }

    /*@GetMapping("/registr")
    public String getPageRegist(Model model, @ModelAttribute("users") User user) {
        if (user.getName() != null) {
            model.addAttribute("err", "err");
        }
        try {
            userService.getCurrentUser();
            model.addAttribute("admin", true);
        } catch (Exception ex) {
            model.addAttribute("admin", false);
        }
        model.addAttribute("reg", true);
        model.addAttribute("users", user);
        return "forUser/registration";
    }

    //Добавление нового пользователя
    @PostMapping("/registr")
    public String addUser(Model model, @ModelAttribute("users") @Valid User user,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("users", user);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("reg", true);
            return "redirect:/registr";
        } else {
            try {
                if (user.getRole() == null) {
                    user.setRole(User.ROLE_BUYER);
                }
                userService.registerUser(user);
                return "redirect:/goodsPage";
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                return "redirect:/registr";
            }
        }
    }*/

    //Личный кабинет пользователя
    @RolesAllowed({"ADMIN", "BUYER"})
    @GetMapping("/usersOne/{id}")
    public String getUser(Model model, @PathVariable Long id) {
        User user = null;
        try {
            user = userService.findById(id);
            model.addAttribute("checkRole", user.getRole().equals(User.ROLE_BUYER));
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
    @GetMapping("/user/upPurse")
    public String getUpPurse(Model model) {
        model = helpWorkWithWallet(true, model);
        return "forUser/workWithWallet";
    }

    //Пополнение кошелька
    @PostMapping("/user/upPurse")
    public String postUpPurse(Model model, @ModelAttribute("users") User user) {
        try {
            User newUser = userService.getCurrentUser();
            newUser.setPurse(user.getPurse() + newUser.getPurse());
            userService.update(newUser);
            return "redirect:/usersOne/" + newUser.getId();
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("up", true);
            return "forUser/userOne";
        }
    }

    //Вывод средств
    @GetMapping("/user/cashWithdrawal")
    public String getCashWithdrawal(Model model) {
        model = helpWorkWithWallet(false, model);
        return "forUser/workWithWallet";
    }

    //Списание с кошелька
    @PostMapping("/user/cashWithdrawal")
    public String postCashWithdrawal(Model model, @Valid User user) {
        try {
            User newUser = userService.getCurrentUser();
            if (user.getPurse() > newUser.getPurse()) {
                model.addAttribute("up", false);
                model.addAttribute("errorMessage", "Вывод средств невозможен!");
                model.addAttribute("users", newUser);
                return "forUser/workWithWallet";
            }
            newUser.setPurse(newUser.getPurse() - user.getPurse());
            userService.update(newUser);
            return "redirect:/usersOne/" + newUser.getId();
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("up", false);
            return "forUser/userOne";
        }
    }





    //Список всех пользователей
    @GetMapping("/admin/users")
    public String getViewOfItem(Model model) {
        model.addAttribute("users", userService.findAll());
        return "forAdmin/user/userList";
    }

    //Переход к определённому товару
    @GetMapping("/admin/users/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        User user = null;
        try {
            user = userService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("users", user);
        return "forAdmin/user/userOne";
    }

    //Добавление нового пользователя
    @GetMapping("/users/add")
    public String showAddUser(Model model, @ModelAttribute("users") User user) {
        if (user.getName() != null) {
            model.addAttribute("err", "err");
        }
        try {
            userService.getCurrentUser();
            model.addAttribute("admin", "admin");
        } catch (Exception ex) {
        }
        model.addAttribute("users", user);
        model.addAttribute("add", true);
        return "forAdmin/user/userEdit";
    }

    //Добавление нового типа товара
    @PostMapping("/users/add")
    public String addUsers(Model model,
                           @ModelAttribute("users") @Valid User user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("users", user);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/users/add";
        } else {
            boolean checkRole = false;
            try {
                if (user.getRole() == null) {
                    user.setRole(User.ROLE_BUYER);
                    checkRole = true;
                }
                User newUser = userService.save(user);
                if (checkRole) {
                    return "redirect:/goodsPage";
                }
                return "redirect:/admin/users/" + String.valueOf(newUser.getId());
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", true);
                return "forAdmin/user/userEdit";
            }
        }
    }

    //Редактирование пользователя
    @GetMapping("/users/{id}/edit")
    public String showEditUser(Model model, @PathVariable Long id) {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        User tmpUser = userService.getCurrentUser();
        if (tmpUser.getRole().equals(User.ROLE_ADMIN)) {
            model.addAttribute("admin", "admin");
        }
        model.addAttribute("add", false);
        model.addAttribute("users", user);
        return "forAdmin/user/userEdit";
    }

    //Редактирование пользователя
    @PostMapping("/users/{id}/edit")
    public String updateUser(Model model, @PathVariable Long id,
                             @ModelAttribute("users") @Valid User user,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("users", user);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/users/" + id + "/edit";
        } else {
            try {
                userService.update(user);
                User tmpUser = userService.getCurrentUser();
                if (tmpUser.getRole().equals(User.ROLE_ADMIN)) {
                    return "redirect:/admin/users/" + String.valueOf(user.getId());
                }
                return "redirect:/usersOne/" + user.getId();
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "forAdmin/user/userEdit";
            }
        }
    }

    //Удаление пользователя
    @GetMapping("/admin/users/{id}/delete")
    public String showDeleteUserById(Model model, @PathVariable Long id) {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("users", user);
        return "forAdmin/user/userOne";
    }

    //Удаление пользователя
    @PostMapping("/admin/users/{id}/delete")
    public String deleteUsersById(Model model, @PathVariable Long id) {
        try {
            userService.deleteById(id);
            return "redirect:/admin/users";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "forAdmin/user/userOne";
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}