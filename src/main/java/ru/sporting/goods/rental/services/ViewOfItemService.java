package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.entities.ViewOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.ViewOfItemRepository;
import ru.sporting.goods.rental.repositories.ViewOfItemRepository;

import java.util.List;

@Service
public class ViewOfItemService {

    private ViewOfItemRepository viewOfItemRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return viewOfItemRepository.existsById(id);
    }

    //Получение записи по id, иначе null
    public ViewOfItem findById(Long id){
        return viewOfItemRepository.findById(id)
                .orElse(null);
    }

    //Получение всех записей
    public List<ViewOfItem> findAll(){
        return viewOfItemRepository.findAll();
    }

    //Добавление новой записи
    public ViewOfItem save(ViewOfItem viewOfItem){
        return viewOfItemRepository.save(viewOfItem);
    }

    //Редактирование записи
    public ViewOfItem update(ViewOfItem viewOfItem) throws Exception{
        if (viewOfItem.getId() != null && !existsById(viewOfItem.getId())){
            throw new Exception("Записи с номером " + viewOfItem.getId() + " не найдена!");
        }
        return viewOfItemRepository.save(viewOfItem);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            viewOfItemRepository.deleteById(id);
        }
    }

    @Autowired
    public void setViewOfItemRepository(ViewOfItemRepository viewOfItemRepository){
        this.viewOfItemRepository = viewOfItemRepository;
    }
}
