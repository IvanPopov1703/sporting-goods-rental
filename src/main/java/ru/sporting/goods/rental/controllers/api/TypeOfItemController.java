package ru.sporting.goods.rental.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.services.TypeOfItemService;

import java.util.List;

@Api(value = "Работа с типом товара", tags = {"Тип товара"})
@RestController
@RequestMapping("/api")
public class TypeOfItemController {

    private TypeOfItemService typeOfItemService;

    @ApiOperation("Получение списка всех типов товара")
    @GetMapping("/productType")
    public ResponseEntity<List<TypeOfItem>> getProductType(){
        return ResponseEntity.ok(typeOfItemService.findAll());
    }

    @ApiOperation("Получение одного товара по id")
    @GetMapping("/productType/{id}")
    public ResponseEntity<TypeOfItem> getProductTypeOne(@PathVariable Long id){
        return ResponseEntity.ok(typeOfItemService.findById(id));
    }

    @ApiOperation("Добавление нового типа товара")
    @PostMapping("/productType")
    public ResponseEntity<TypeOfItem> addProductType(@RequestBody TypeOfItem product){
        typeOfItemService.save(product);
        return ResponseEntity.ok(product);
    }

    @ApiOperation("Редактирование типа товара")
    @PutMapping("/productType")
    public ResponseEntity updateTypeProduct(@RequestBody TypeOfItem product){
        try{
            //typeOfItemService.update(product);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление товара по id")
    @DeleteMapping("/productType/{id}")
    public ResponseEntity deleteProductType(@PathVariable Long id){
        //typeOfItemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setTypeOfItemService(TypeOfItemService typeOfItemService){
        this.typeOfItemService = typeOfItemService;
    }
}
