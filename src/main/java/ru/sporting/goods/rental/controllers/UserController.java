package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.services.UserService;

@Api(value = "Работа с пользователем", tags = {"Пользователь"})
@RestController
public class UserController {

    @Autowired
    UserService userService;


}
