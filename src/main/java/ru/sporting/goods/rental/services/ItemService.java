package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.exceptions.ItemNotFoundException;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
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
                .orElseThrow(()-> new RecordNotFound(id));
    }

    //Добавление товара
    public void addItem(Items item){
        itemRepository.save(item);
    }

    //Метод для проверки наличия типа товара в базе
    private boolean checkRecordToBase(Long id){
        return itemRepository.existsById(id);
    }

    //Изменение товара
    public void updateItem(Items item){
        if(checkRecordToBase(item.getId())){
            itemRepository.save(item);
        } else {
            throw new RecordNotFound(item.getId());
        }
    }

    //Удаление товара
    public void deleteItem(Long id){
        if(checkRecordToBase(id)){
            itemRepository.deleteById(id);
        } else {
            throw new RecordNotFound(id);
        }
    }
}
