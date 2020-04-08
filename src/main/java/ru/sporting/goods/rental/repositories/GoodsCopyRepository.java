package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.InstanceOfItem;

public interface GoodsCopyRepository extends JpaRepository<InstanceOfItem, Long> {
}
