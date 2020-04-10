package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
