package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.services.ViewOfItemService;

import java.util.List;

@Api(value = "Работа с видом товара", tags = {"Вид товара"})
@RestController
public class ViewOfItemController {

    private ViewOfItemService viewOfItemService;

    @ApiOperation("Получение списка всех типов товара")
    @GetMapping("/viewItem")
    public ResponseEntity<List<ViewOfItem>> getProductView() {
        return ResponseEntity.ok(viewOfItemService.getAll());
    }

    @ApiOperation("Получение одного товара по id")
    @GetMapping("/viewItem/{id}")
    public ResponseEntity<ViewOfItem> getProductViewOne(@PathVariable Long id) {
        return ResponseEntity.ok(viewOfItemService.getOne(id));
    }

    @ApiOperation("Добавление нового типа товара")
    @PostMapping("/viewItem")
    public ResponseEntity<ViewOfItem> addProductView(@RequestBody ViewOfItem view) {
        viewOfItemService.addViewOfItem(view);
        return ResponseEntity.ok(view);
    }

    @ApiOperation("Редактирование вида товара")
    @PutMapping("/viewItem")
    public ResponseEntity updateTypeProduct(@RequestBody ViewOfItem view) {
        try {
            viewOfItemService.update(view);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление вида по id")
    @DeleteMapping("/viewItem/{id}")
    public ResponseEntity deleteViewProduct(@PathVariable Long id) {
        viewOfItemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setViewOfItemService(ViewOfItemService viewOfItemService) {
        this.viewOfItemService = viewOfItemService;
    }
}
