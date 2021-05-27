package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.TypeOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
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
    public TypeOfItem findById(Long id) throws RecordNotFound{
        return typeOfItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound(id));
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
    public TypeOfItem update(TypeOfItem typeOfItem) throws RecordNotFound{
        if (typeOfItem.getId() != null && !existsById(typeOfItem.getId())){
            throw new RecordNotFound(typeOfItem.getId());
        }
        return typeOfItemRepository.save(typeOfItem);
    }

    //Удаление записи
    public void deleteById(Long id) throws RecordNotFound {
        if (!existsById(id)){
            throw new RecordNotFound(id);
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
