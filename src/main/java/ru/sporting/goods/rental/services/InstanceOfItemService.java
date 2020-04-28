package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.InstanceOfItemRepository;

import java.util.List;

@Service
public class InstanceOfItemService {

    @Autowired
    InstanceOfItemRepository instanceOfItemRepository;

    //Получение всех товаров
    public List<InstanceOfItem> getAll(){
        return instanceOfItemRepository.findAll();
    }

    //Получение одного товара
    public InstanceOfItem getOne(Long id){
        return instanceOfItemRepository.findById(id)
                .orElseThrow(()-> new RecordNotFound(id));
    }

    //Метод для проверки наличия типа товара в базе
    private boolean checkRecordToBase(Long id){
        return instanceOfItemRepository.existsById(id);
    }

    //Добавление нового товара
    public void add(InstanceOfItem instanceOfItem){
        instanceOfItemRepository.save(instanceOfItem);
    }

    //Редактирование товара
    public void update(InstanceOfItem instanceOfItem){
        if(checkRecordToBase(instanceOfItem.getId())){
            instanceOfItemRepository.save(instanceOfItem);
        }
        else {
            throw  new RecordNotFound(instanceOfItem.getId());
        }
    }

    //Удаление товара
    public void delete(Long id){
        if(checkRecordToBase(id)){
            instanceOfItemRepository.deleteById(id);
        }
        else {
            throw  new RecordNotFound(id);
        }
    }
}
