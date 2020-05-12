package ru.sporting.goods.rental.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.services.InstanceOfItemService;

import java.util.List;

@Api(value = "Работа с экземплярами товаров", tags = {"Экземпляр товара"})
@RestController
@RequestMapping("/api")
public class InstanceOfItemController {

    private InstanceOfItemService instanceOfItemService;

    @ApiOperation("Получение всех товаров")
    @GetMapping("/instance")
    public ResponseEntity<List<InstanceOfItem>> getAllInstanse(){
        return ResponseEntity.ok(instanceOfItemService.getAll());
    }

    @ApiOperation("Получение товаров по id")
    @GetMapping("/instance/{id}")
    public ResponseEntity<InstanceOfItem> getOneInstance(@PathVariable Long id){
        return ResponseEntity.ok(instanceOfItemService.getOne(id));
    }

    @ApiOperation("Добавление нового товара")
    @PostMapping("/instance")
    public ResponseEntity<InstanceOfItem> addInstance(@RequestBody InstanceOfItem instance){
        instanceOfItemService.add(instance);
        return ResponseEntity.ok(instance);
    }

    @ApiOperation("Обновление товара")
    @PutMapping("/instance")
    public ResponseEntity updateInstance(@RequestBody InstanceOfItem instanceOfItem){
        try {
            instanceOfItemService.update(instanceOfItem);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление товара")
    @DeleteMapping("/instance/{id}")
    public ResponseEntity deleteInstance(@PathVariable Long id){
        instanceOfItemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setInsnceOfItemService(InstanceOfItemService insnceOfItemService){
        this.instanceOfItemService = insnceOfItemService;
    }
}
