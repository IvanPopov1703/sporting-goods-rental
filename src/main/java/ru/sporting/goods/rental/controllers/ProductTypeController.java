package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sporting.goods.rental.model.ProductType;
import ru.sporting.goods.rental.services.ProductTypeService;

import java.util.List;

@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/productType")
    public ResponseEntity<List<ProductType>> getProductType(){
        return ResponseEntity.ok(productTypeService.getAll());
    }
}
