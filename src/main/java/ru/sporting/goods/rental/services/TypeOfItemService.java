package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.repositories.TypeOfItemRepository;

import java.util.List;

@Service
public class TypeOfItemService {

    private TypeOfItemRepository typeOfItemRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return typeOfItemRepository.existsById(id);
    }

    //Получение записи по id, иначе null
    public TypeOfItem findById(Long id){
        return typeOfItemRepository.findById(id)
                .orElse(null);
    }

    //Получение всех записей
    public List<TypeOfItem> findAll(){
        return typeOfItemRepository.findAll();
    }

    //Добавление новой записи
    public TypeOfItem save(TypeOfItem typeOfItem){
        return typeOfItemRepository.save(typeOfItem);
    }

    //Редактирование записи
    public TypeOfItem update(TypeOfItem typeOfItem) throws Exception{
        if (typeOfItem.getId() != null && !existsById(typeOfItem.getId())){
            throw new Exception("Записи с номером " + typeOfItem.getId() + " не найдена!");
        }
        return typeOfItemRepository.save(typeOfItem);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            typeOfItemRepository.deleteById(id);
        }
    }

    @Autowired
    public void setTypeOfItemRepository(TypeOfItemRepository typeOfItemRepository){
        this.typeOfItemRepository = typeOfItemRepository;
    }
}
