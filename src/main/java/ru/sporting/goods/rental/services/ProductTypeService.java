package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.model.ProductType;
import ru.sporting.goods.rental.repositories.ProductTypeRepository;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    //Получение всех типов
    public List<ProductType> getAll(){
        return productTypeRepository.findAll();
    }
}
