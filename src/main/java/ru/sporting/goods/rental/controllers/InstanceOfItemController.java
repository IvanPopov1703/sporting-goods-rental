package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.services.InstanceOfItemService;

@Api(value = "Работа с экземплярами товаров", tags = {"Экземпляр товара"})
@RestController
public class InstanceOfItemController {

    @Autowired
    InstanceOfItemService instanceOfItemService;


}
