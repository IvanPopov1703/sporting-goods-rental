package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.repositories.OrderRepository;

import java.sql.Struct;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    //Проверка наличия записи в базе
    private boolean existsById(Long id){
        return orderRepository.existsById(id);
    }

    //Получение записи по id, иначе null
    public Orders findById(Long id){
        return orderRepository.findById(id)
                .orElse(null);
    }

    //Получение всех записей
    public List<Orders> findAll(){
        return orderRepository.findAll();
    }

    //Добавление новой записи
    public Orders save(Orders orders){
        return orderRepository.save(orders);
    }

    //Редактирование записи
    public Orders update(Orders orders) throws Exception{
        if (orders.getId() != null && !existsById(orders.getId())){
            throw new Exception("Записи с номером " + orders.getId() + " не найдена!");
        }
        return orderRepository.save(orders);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception{
        if (!existsById(id)){
            throw new Exception("Запись с номером " + id + " не найдена!");
        }
        else {
            orderRepository.deleteById(id);
        }
    }

    //Проверка хватает ли денег для покупки
    public boolean checkManyForBuy(Orders orders){
        return false;
    }

    //Метод определения количества дней между датами
    public boolean getCountDayRental(Orders orders){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(orders.getPlannedTimeOfReturningProduct().toString(), formatter);
        LocalDate endDate = LocalDate.parse(orders.getTimeOfReceiptOfItem().toString(), formatter);
        Period period = Period.between(startDate, endDate);
        double orderCost = period.getDays() * orders.getInstance().getItems().getСostOneDayRental() + Items.AMOUNT_OF_GUARANTEE;
        if (orderCost <= orders.getUser().getPurse()){
            orders.getUser().setPurse(orders.getUser().getPurse() - orderCost);
            orders.setOrderCost(orderCost);
            return true;
        }
        return false;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
