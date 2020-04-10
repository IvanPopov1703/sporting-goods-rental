package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.Item;
import ru.sporting.goods.rental.services.ItemService;

import java.util.List;

@Api(value = "Работа с товарами", tags = {"Товар"})
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("Получение списка товаров")
    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAll());
    }

    @ApiOperation("Получение товаров по id")
    @GetMapping("/item/{id}")
    public  ResponseEntity<Item> getItem(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getOne(id));
    }

    @ApiOperation("Добавление товара")
    @PostMapping("/item")
    public ResponseEntity<Object> addItem(@RequestBody Item item){
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }

    @ApiOperation("Изменение товара")
    @PutMapping("/item")
    public ResponseEntity updateItem(@RequestBody Item item){
        try {
            itemService.updateItem(item);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление товара")
    @PutMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

}
