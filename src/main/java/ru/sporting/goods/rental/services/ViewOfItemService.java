package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.exceptions.TypeOfItemNotFoundException;
import ru.sporting.goods.rental.repositories.ViewOfItemRepository;

import java.util.List;

@Service
public class ViewOfItemService {

    @Autowired
    ViewOfItemRepository viewOfItemRepository;

    //Получение всех типов товара
    public List<ViewOfItem> getAll(){
        return viewOfItemRepository.findAll();
    }

    //Получение одного продукта по id
    public ViewOfItem getOne(Long id){
        return viewOfItemRepository.findById(id)
                .orElseThrow(() -> new TypeOfItemNotFoundException(id));
    }

    //Добавление нового типа товара
    public void addProductType(ViewOfItem item){
        viewOfItemRepository.save(item);
    }

    //Редактирование типа товара
    public void updateTypeProduct(ViewOfItem item){
        boolean exists = viewOfItemRepository.existsById(item.getId());
        if (exists){
            viewOfItemRepository.save(item);
        } else{
            throw new TypeOfItemNotFoundException(item.getId());
        }
    }

    //Удаление типа продукта по id
    public void deleteProductTypeById(Long id) {
        try {
            viewOfItemRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new TypeOfItemNotFoundException(id);
        }
    }
}
