package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.repositories.ItemRepository;
import ru.sporting.goods.rental.services.ItemService;

import java.util.List;

@Api(value = "Работа с товарами", tags = {"Товар"})
@RestController
@RequestMapping("/api")
public class ItemController {

    private ItemService itemService;

    @ApiOperation("Получение списка товаров")
    @GetMapping("/item")
    public ResponseEntity<List<Items>> getAllItem(){
        return ResponseEntity.ok(itemService.getAll());
    }

    @ApiOperation("Получение товаров по id")
    @GetMapping("/item/{id}")
    public  ResponseEntity<Items> getItem(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getOne(id));
    }

    @ApiOperation("Добавление товара")
    @PostMapping("/item")
    public ResponseEntity<Items> addItem(@RequestBody Items item){
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }

    @ApiOperation("Изменение товара")
    @PutMapping("/item")
    public ResponseEntity updateItem(@RequestBody Items item){
        try {
            itemService.updateItem(item);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление товара")
    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    }

}
