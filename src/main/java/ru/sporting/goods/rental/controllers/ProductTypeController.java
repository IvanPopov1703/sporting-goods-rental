package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.ProductTypeService;

import java.util.List;

@Api(value = "Работа с типом товара", tags = {"Тип товара"})
@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @ApiOperation("Получение списка всех типов товара")
    @GetMapping("/productType")
    public ResponseEntity<List<TypeOfItem>> getProductType(){
        return ResponseEntity.ok(productTypeService.getAll());
    }

    @ApiOperation("Получение одного товара по id")
    @GetMapping("/productType/{id}")
    public ResponseEntity<TypeOfItem> getProductTypeOne(@PathVariable Long id){
        return ResponseEntity.ok(productTypeService.getOne(id));
    }

    @ApiOperation("Добавление нового типа товара")
    @PostMapping("/productType")
    public ResponseEntity<Object> addProductType(@RequestBody TypeOfItem product){
        productTypeService.addProductType(product);
        return ResponseEntity.ok(product);
    }

    @ApiOperation("Редактирование типа товара")
    @PutMapping("/productType")
    public ResponseEntity updateTypeProduct(@RequestBody TypeOfItem product){
        try{
            productTypeService.updateTypeProduct(product);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление товара по id")
    @DeleteMapping("/productType/{id}")
    public ResponseEntity deleteProductType(@PathVariable Long id){
        productTypeService.deleteProductTypeById(id);
        return ResponseEntity.ok().build();
    }

}
