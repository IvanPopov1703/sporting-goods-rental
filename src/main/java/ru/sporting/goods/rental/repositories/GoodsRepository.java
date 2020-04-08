package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.Item;

public interface GoodsRepository extends JpaRepository<Item, Long> {
}
