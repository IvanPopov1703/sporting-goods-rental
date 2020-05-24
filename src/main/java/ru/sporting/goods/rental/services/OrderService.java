package ru.sporting.goods.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sporting.goods.rental.entities.InstanceOfItem;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.Orders;
import ru.sporting.goods.rental.entities.User;
import ru.sporting.goods.rental.repositories.OrderRepository;

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
                order.getInstance().setOrder_status(InstanceOfItem.STATUS_ORDER_HAND_OVER);
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
                /*user.setFine(user.getFine() + (getCountDayByDate(order.getTimeOfReceiptOfItem(),
                        order.getPlannedTimeOfReturningProduct()) *
                        order.getInstance().getItems().getСostOneDayRental()));*/
            }
            else {
                resultList.add(order);
            }
        }
        return resultList;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
