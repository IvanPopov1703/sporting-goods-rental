package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.services.RoleService;

@Api(value = "Работа с ролью пользователей", tags = {"Роль пользователя"})
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;


}
