package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.repositories.OrderRepository;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private InstanceOfItemService instanceOfItemService;

    //Проверка наличия записи в базе
    private boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    //Получение записи по id, иначе null
    public Orders findById(Long id) {
        return orderRepository.findById(id)
                .orElse(null);
    }

    //Получение всех записей
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    //Добавление новой записи
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    //Редактирование записи
    public void update(Orders orders) {
        orderRepository.save(orders);
    }

    //Удаление записи
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Запись с номером " + id + " не найдена!");
        } else {
            orderRepository.deleteById(id);
        }
    }

    //Функция для вычисления количества дней
    public int getCountDayByDate(Date dateStart, Date dateEnd){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart.toString(), formatter);
        LocalDate endDate = LocalDate.parse(dateEnd.toString(), formatter);
        Period period = Period.between(startDate, endDate);
        return period.getDays();
    }

    //Метод определения количества дней между датами
    public boolean getCountDayRental(Orders orders) {
        double orderCost = getCountDayByDate(orders.getTimeOfReceiptOfItem(),
                orders.getPlannedTimeOfReturningProduct()) *
                orders.getInstance().getItems().getСostOneDayRental() + Items.AMOUNT_OF_GUARANTEE;
        if (orderCost <= orders.getUser().getPurse()) {
            orders.getUser().setPurse(orders.getUser().getPurse() - orderCost);
            orders.setOrderCost(orderCost);
            return true;
        }
        return false;
    }

    //Получение заказов по id пользователя и статусу заказа
    public List<Orders> getOrdersByIdUserAndStatusInstance(Long id, String status) {
        return orderRepository.getOrdersByIdUserAndStatusInstance(status, id);
    }

    //Перебор всех товаров со статусом Pending
    public List<Orders> checkOrdersPending(User user){
        List<Orders> ordersList = getOrdersByIdUserAndStatusInstance(user.getId(),
                InstanceOfItem.STATUS_ORDER_PENDING);
        List<Orders> resultList = new ArrayList<Orders>();
        for (Orders order : ordersList) {
            if (order.getPlannedTimeOfReturningProduct().getTime() < Date.valueOf(LocalDate.now()).getTime()){
                user.setPurse(user.getPurse() + Items.AMOUNT_OF_GUARANTEE);
                order.setRealTimeOfReturningProduct(order.getPlannedTimeOfReturningProduct());
                order.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_HAND_OVER);
                instanceOfItemService.update(order.getInstance());
            }
            else {
                resultList.add(order);
            }
        }
        return resultList;
    }

    //Перебор всех товаров со статусом Issued
    public List<Orders> checkOrdersIssued(User user){
        List<Orders> ordersList = getOrdersByIdUserAndStatusInstance(user.getId(),
                InstanceOfItem.STATUS_ORDER_ISSUED);
        List<Orders> resultList = new ArrayList<Orders>();
        for (Orders order : ordersList) {
            if (order.getPlannedTimeOfReturningProduct().getTime() < Date.valueOf(LocalDate.now()).getTime()){
                order.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_EXPIRED);
                order.setFine(order.getFine() + getCountDayByDate(order.getPlannedTimeOfReturningProduct(), Date.valueOf(LocalDate.now())) *
                        order.getInstance().getItems().getСostOneDayRental());
                instanceOfItemService.update(order.getInstance());
            }
            else {
                resultList.add(order);
            }
        }
        return resultList;
    }

    //Вернуть заказ (статус Issued)
    public Long returnGoodWithIssuedStatus(Long id) {
        Orders orders = findById(id);
        InstanceOfItem instance = instanceOfItemService.findById(orders.getInstance().getId());
        User user = userService.findById(orders.getUser().getId());
        //Установка реального времени сдачи
        orders.setRealTimeOfReturningProduct(Date.valueOf(LocalDate.now()));
        //Установка статуса Сдан экземпляру
        instance.setOrder_status(InstanceOfItem.STATUS_ORDER_HAND_OVER);
        //Возмещение пользователю денежных средств
        user.setPurse(user.getPurse() +
                (getCountDayByDate(Date.valueOf(LocalDate.now()),
                        orders.getPlannedTimeOfReturningProduct()) *
                        instance.getItems().getСostOneDayRental())
                + Items.AMOUNT_OF_GUARANTEE);
        //Добавление к экземпляру часов пользования
        instance.setHoursOfUse(instance.getHoursOfUse() + getCountDayByDate(orders.getTimeOfReceiptOfItem(),
                Date.valueOf(LocalDate.now())) * InstanceOfItem.DAY_RENTAL);
        //Обновление данных в таблицах
        update(orders);
        userService.update(user);
        instanceOfItemService.update(instance);
        return user.getId();
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setInstanceOfItemService(InstanceOfItemService instanceOfItemService){
        this.instanceOfItemService = instanceOfItemService;
    }
}
