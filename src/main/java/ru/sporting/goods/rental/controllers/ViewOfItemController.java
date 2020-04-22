package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.services.ViewOfItemService;

import java.util.List;

@Api(value = "Работа с видом товара", tags = {"Вид товара"})
@RestController
public class ViewOfItemController {

    @Autowired
    private ViewOfItemService viewOfItemService;

    
}
