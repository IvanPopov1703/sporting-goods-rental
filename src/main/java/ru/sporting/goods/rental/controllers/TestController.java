package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.services.ProductTypeService;

@Api(value = "Работа с товарами", tags = {"Товар"})
@Controller
public class TestController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/")
    public String getMainPage(){
        return "mainPage";
    }
}
