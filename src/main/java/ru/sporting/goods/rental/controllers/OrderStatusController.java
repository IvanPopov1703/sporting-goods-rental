package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.services.OrderStatusService;

@Api(value = "Работа со статусом заказа", tags = {"Статус заказа"})
@RestController
public class OrderStatusController {

    @Autowired
    OrderStatusService orderStatusService;


}
