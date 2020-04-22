package ru.sporting.goods.rental.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.entities.TestEmpty;
import ru.sporting.goods.rental.services.TestEmptyService;

import java.util.List;

@Api(value = "Работа с тестовой сущностью", tags = {"Тестовая сущность"})
@RestController
public class TestEmptyController {

    @Autowired
    TestEmptyService testEmptyService;

    @ApiOperation("Получение всех")
    @GetMapping("/TestEmpty")
    public ResponseEntity<List<TestEmpty>> getAll(){
        return ResponseEntity.ok(testEmptyService.getAll());
    }

    @ApiOperation("Добавление новой")
    @PostMapping("/testEmpty")
    public ResponseEntity<TestEmpty> saveRec(@RequestBody TestEmpty empty){
        testEmptyService.add(empty);
        return ResponseEntity.ok(empty);
    }

    //Связь с users

}
