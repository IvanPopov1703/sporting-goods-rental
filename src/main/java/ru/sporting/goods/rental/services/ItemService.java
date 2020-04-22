package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.exceptions.ItemNotFoundException;
import ru.sporting.goods.rental.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    //Получение всех товаров
    public List<Items> getAll(){
        return itemRepository.findAll();
    }

    //Получение товара по id
    public Items getOne(Long id){
        return itemRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(id));
    }

    //Добавление товара
    public void addItem(Items item){
        itemRepository.save(item);
    }

    //Изменение товара
    public void updateItem(Items item){
        boolean exists = itemRepository.existsById(item.getId());
        if (exists){
            itemRepository.save(item);
        } else {
            throw new ItemNotFoundException(item.getId());
        }
    }

    //Удаление товара
    public void deleteItem(Long id){
        try {
            itemRepository.deleteById(id);
        } catch (IllegalArgumentException e){
            throw new ItemNotFoundException(id);
        }
    }
}
