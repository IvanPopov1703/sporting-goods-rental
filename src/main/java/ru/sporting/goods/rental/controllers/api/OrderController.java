package ru.sporting.goods.rental.controllers.api;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.services.OrderService;

@Api(value = "Работа с заказом", tags = {"Заказ"})
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
}
