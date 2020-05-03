package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.OrderStatus;
import ru.sporting.goods.rental.exceptions.RecordNotFound;
import ru.sporting.goods.rental.repositories.OrderStatusRepository;

import java.util.List;

@Service
public class OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    //Получение всех статусов
    public List<OrderStatus> getAllStatus(){
        return orderStatusRepository.findAll();
    }

    //Получение одного статуса по id
    public OrderStatus getOneStatus(Long id){
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound(id));
    }

    //Добавление нового статуса
    public void addStatus(OrderStatus status){
        orderStatusRepository.save(status);
    }

    //Метод для проверки наличия статуса
    private boolean checkRecordToBase(Long id){
        return orderStatusRepository.existsById(id);
    }

    //Редактирование статуса
    public void updateStatus(OrderStatus status){
        if (checkRecordToBase(status.getId())){
            orderStatusRepository.save(status);
        } else{
            throw new RecordNotFound(status.getId());
        }
    }

    //Удаление статуса по id
    public void deleteStatusById(Long id) {
        if (checkRecordToBase(id)){
            orderStatusRepository.deleteById(id);
        } else{
            throw new RecordNotFound(id);
        }
    }
}
