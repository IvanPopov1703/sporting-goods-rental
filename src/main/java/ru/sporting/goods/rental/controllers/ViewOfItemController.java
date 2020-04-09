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

    @ApiOperation("Получение списка всех видов товара")
    @GetMapping("/viewItem")
    public ResponseEntity<List<ViewOfItem>> getProductType(){
        return ResponseEntity.ok(viewOfItemService.getAll());
    }

    @ApiOperation("Получение одного вида товара по id")
    @GetMapping("/viewItem/{id}")
    public ResponseEntity<ViewOfItem> getProductTypeOne(@PathVariable Long id){
        return ResponseEntity.ok(viewOfItemService.getOne(id));
    }

    @ApiOperation("Добавление нового вида товара")
    @PostMapping("/viewItem")
    public ResponseEntity<Object> addProductType(@RequestBody ViewOfItem viewOfItem){
        viewOfItemService.addProductType(viewOfItem);
        return ResponseEntity.ok(viewOfItem);
    }

    @ApiOperation("Редактирование вида товара товара")
    @PutMapping("/viewItem")
    public ResponseEntity updateTypeProduct(@RequestBody ViewOfItem viewOfItem){
        try{
            viewOfItemService.updateTypeProduct(viewOfItem);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление вида товара по id")
    @DeleteMapping("/viewItem/{id}")
    public ResponseEntity deleteProductType(@PathVariable Long id){
        viewOfItemService.deleteProductTypeById(id);
        return ResponseEntity.ok().build();
    }
}
