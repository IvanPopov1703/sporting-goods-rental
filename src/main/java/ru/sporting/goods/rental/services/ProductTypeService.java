package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sporting.goods.rental.Exceptions.ProductTypeNotFoundException;
import ru.sporting.goods.rental.model.ProductType;
import ru.sporting.goods.rental.repositories.ProductTypeRepository;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    //Получение всех типов товара
    public List<ProductType> getAll(){
        return productTypeRepository.findAll();
    }

    //Получение одного продукта по id
    public ProductType getOne(Long id){
        return productTypeRepository.findById(id)
                .orElseThrow(() -> new ProductTypeNotFoundException(id));
    }

    //Добавление нового типа товара
    public void addProductType(ProductType product){
        productTypeRepository.save(product);
    }

    //Редактирование типа товара
    public void updateTypeProduct(ProductType product){
        boolean exists = productTypeRepository.existsById(product.getId());
        if (exists){
            productTypeRepository.save(product);
        } else{
            throw new ProductTypeNotFoundException(product.getId());
        }
    }

    //Удаление типа продукта по id
    public void deleteProductTypeById(Long id) {
        productTypeRepository.deleteById(id);
    }
}
