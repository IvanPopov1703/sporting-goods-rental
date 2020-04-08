package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
