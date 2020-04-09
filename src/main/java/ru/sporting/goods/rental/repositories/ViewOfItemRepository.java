package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.ViewOfItem;

public interface ViewOfItemRepository extends JpaRepository<ViewOfItem, Long> {
}
