package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.InstanceOfItemRepository;

import java.util.List;

@Service
public class InstanceOfItemService {

    private InstanceOfItemRepository instanceOfItemRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return instanceOfItemRepository.existsById(id);
    }

    //Получение всех товаров
    public List<InstanceOfItem> findAll(){
        return instanceOfItemRepository.findAll();
    }

    //Получение товара по id
    public InstanceOfItem findById(Long id){
        return instanceOfItemRepository.findById(id)
                .orElseThrow(()-> new RecordNotFound(id));
    }

    //Добавление товара
    public InstanceOfItem save(InstanceOfItem item){
        return instanceOfItemRepository.save(item);
    }

    //Редактирование записи
    public void update(InstanceOfItem item) {
        instanceOfItemRepository.save(item);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            instanceOfItemRepository.deleteById(id);
        }
    }

    //Получение всех экземпляров товаров вместе с именами типов и видов товара
    public List<InstanceOfItem> findAllInstanceAndItemsAndTypeAndView(){
        return instanceOfItemRepository.findAllInstanceAndItemsAndTypeAndView();
    }

    //Получение получение экземпляра по id
    public InstanceOfItem findInstanceOfItemById(Long id){
        return instanceOfItemRepository.findInstancesOfItemById(id);
    }

    //Получение количества копий товара по id товара и статусу
    public int getNumberOfCopiesByIdItem(String status, Long id){
        return instanceOfItemRepository.getNumberOfCopiesByIdItem(status, id);
    }

    //Получение всех экземпляров по id товара
    public List<InstanceOfItem> findInstancesOfItemByIdItems(Long id){
        return instanceOfItemRepository.findInstancesOfItemByIdItems(id);
    }

    //Получение списка экземпляров товара по id товара и статусу
    public List<InstanceOfItem> getListOfCopiesByIdItemAndStatus(Long id, String status){
        return instanceOfItemRepository.getListOfCopiesByIdItemAndStatus(status, id);
    }

    @Autowired
    public void setInstanceOfItemRepository(InstanceOfItemRepository instanceOfItemRepository){
        this.instanceOfItemRepository = instanceOfItemRepository;
    }
}
