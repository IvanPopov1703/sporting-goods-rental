package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.model.Goods;
import ru.sporting.goods.rental.services.GoodsService;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getGoods(){
        return ResponseEntity.ok(goodsService.getAll());
    }
}
