package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.Items;

public interface ItemRepository extends JpaRepository<Items, Long> {
}
