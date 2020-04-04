package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
