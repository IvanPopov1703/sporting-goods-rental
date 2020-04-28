package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
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
                .orElseThrow(() -> new RecordNotFound(id));
    }

    //Добавление нового типа товара
    public void addProductType(TypeOfItem product){
        typeOfItemRepository.save(product);
    }

    //Метод для проверки наличия типа товара в базе
    private boolean checkRecordToBase(Long id){
        return typeOfItemRepository.existsById(id);
    }

    //Редактирование типа товара
    public void updateTypeProduct(TypeOfItem product){
        if (checkRecordToBase(product.getId())){
            typeOfItemRepository.save(product);
        } else{
            throw new RecordNotFound(product.getId());
        }
    }

    //Удаление типа продукта по id
    public void deleteProductTypeById(Long id) {
        if (checkRecordToBase(id)){
            typeOfItemRepository.deleteById(id);
        } else{
            throw new RecordNotFound(id);
        }
    }
}
