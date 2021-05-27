package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return itemRepository.existsById(id);
    }

    //Получение всех товаров
    public List<Items> findAll(){
        return itemRepository.findAll();
    }

    //Получение товара по id
    public Items findById(Long id){
        return itemRepository.findById(id)
                .orElseThrow(()-> new RecordNotFound(id));
    }

    //Добавление товара
    public Items save(Items item){
        return itemRepository.save(item);
    }

    //Редактирование записи
    public Items update(Items item) throws RecordNotFound{
        if (item.getId() != null && !existsById(item.getId())){
            throw new RecordNotFound(item.getId());
        }
        return itemRepository.save(item);
    }

    //Удаление записи
    public void deleteById(Long id) throws RecordNotFound{
        if (!existsById(id)){
            throw new RecordNotFound(id);
        }
        else {
            itemRepository.deleteById(id);
        }
    }

    //Получение всех товаров, где вместо id типа и вида товара, их название
    public List<Items> findAllItemsAndTypeAndView(){
        return itemRepository.findAllItemsAndTypeAndView();
    }

    //Получение одного товара по id, где вместо id типа и вида, их наименование
    public Items findItemById(Long id){
        return itemRepository.findItemById(id);
    }

    //Сортировка товара по виду и типу
    public List<Items> sortItemByIdViewAndType(Long idView, Long idType){
        return itemRepository.sortItemByIdViewAndType(idView, idType);
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
}
