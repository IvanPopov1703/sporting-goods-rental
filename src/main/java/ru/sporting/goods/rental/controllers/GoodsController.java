package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.model.Goods;
import ru.sporting.goods.rental.services.GoodsService;

import java.util.List;

@Api(value = "Работа с товарами", tags = {"Товар"})
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("Получение списка товаров")
    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getGoods(){
        return ResponseEntity.ok(goodsService.getAll());
    }
}
