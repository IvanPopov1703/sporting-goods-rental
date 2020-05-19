package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.OrderStatus;
import ru.sporting.goods.rental.repositories.OrderStatusRepository;

import java.util.List;

@Service
public class OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return orderStatusRepository.existsById(id);
    }

    //Получение записи по id, иначе null
    public OrderStatus findById(Long id){
        return orderStatusRepository.findById(id)
                .orElse(null);
    }

    //Получение всех записей
    public List<OrderStatus> findAll(){
        return orderStatusRepository.findAll();
    }

    //Добавление новой записи
    public OrderStatus save(OrderStatus typeOfItem){
        return orderStatusRepository.save(typeOfItem);
    }

    //Редактирование записи
    public OrderStatus update(OrderStatus typeOfItem) throws Exception{
        if (typeOfItem.getId() != null && !existsById(typeOfItem.getId())){
            throw new Exception("Записи с номером " + typeOfItem.getId() + " не найдена!");
        }
        return orderStatusRepository.save(typeOfItem);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            orderStatusRepository.deleteById(id);
        }
    }

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    }
}
