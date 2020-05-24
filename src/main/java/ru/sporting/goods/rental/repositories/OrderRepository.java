package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sporting.goods.rental.entities.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    //Получение заказов по id пользователя и статусу заказа
    @Query(value = "SELECT o.*, u.*, ins.*, t.*, v.*, i.* " +
            "FROM orders AS o INNER JOIN users AS u ON o.id_user = u.id_user " +
            "INNER JOIN instance_of_item AS ins ON ins.id_instance_of_item = o.id_instance_of_item " +
            "INNER JOIN item AS i ON i.id_item = ins.id_item " +
            "INNER JOIN view_of_item AS v ON v.id_view_of_item = i.view_of_item_id " +
            "INNER JOIN type_of_item AS t ON t.id_type_of_item = i.type_of_item_id " +
            "WHERE (o.id_user = :id) AND (ins.order_status = :status)", nativeQuery = true)
    List<Orders> getOrdersByIdUserAndStatusInstance(@Param("status") String status, @Param("id") Long id);

}
