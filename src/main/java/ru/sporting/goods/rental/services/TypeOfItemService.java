package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.exceptions.TypeOfItemNotFoundException;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.repositories.TypeOfItemRepository;

import java.util.List;

@Service
public class TypeOfItemService {
    @Autowired
    TypeOfItemRepository typeOfItemRepository;

    //Получение всех типов товара
    public List<TypeOfItem> getAll(){
        return typeOfItemRepository.findAll();
    }

    //Получение одного продукта по id
    public TypeOfItem getOne(Long id){
        return typeOfItemRepository.findById(id)
                .orElseThrow(() -> new TypeOfItemNotFoundException(id));
    }

    //Добавление нового типа товара
    public void addProductType(TypeOfItem product){
        typeOfItemRepository.save(product);
    }

    //Редактирование типа товара
    public void updateTypeProduct(TypeOfItem product){
        boolean exists = typeOfItemRepository.existsById(product.getId());
        if (exists){
            typeOfItemRepository.save(product);
        } else{
            throw new TypeOfItemNotFoundException(product.getId());
        }
    }

    //Удаление типа продукта по id
    public void deleteProductTypeById(Long id) {
        try {
            typeOfItemRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new TypeOfItemNotFoundException(id);
        }
    }
}
