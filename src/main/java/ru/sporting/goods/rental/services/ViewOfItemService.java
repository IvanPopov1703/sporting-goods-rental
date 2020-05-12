package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.exceptions.TypeOfItemNotFoundException;
import ru.sporting.goods.rental.repositories.ViewOfItemRepository;

import java.util.List;

@Service
public class ViewOfItemService {

    @Autowired
    ViewOfItemRepository viewOfItemRepository;

    //Добавление вида товара
    public void addViewOfItem(ViewOfItem viewOfItem){
        viewOfItemRepository.save(viewOfItem);
    }

    //Получение всех товаров
    public List<ViewOfItem> findAll(){
        return viewOfItemRepository.findAll();
    }

    //Получение одного товара
    public ViewOfItem getOne(Long id){
        return viewOfItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound(id));
    }

    //Метод для проверки наличия типа товара в базе
    private boolean checkRecordToBase(Long id){
        return viewOfItemRepository.existsById(id);
    }

    //Редактирование товара
    public void update(ViewOfItem item){
        if(checkRecordToBase(item.getId())){
            viewOfItemRepository.save(item);
        }
        else {
            throw new RecordNotFound(item.getId());
        }
    }

    //Удаление вида
    public void delete(Long id){
        if(checkRecordToBase(id)){
            viewOfItemRepository.deleteById(id);
        }
        else {
            throw new RecordNotFound(id);
        }
    }
}
