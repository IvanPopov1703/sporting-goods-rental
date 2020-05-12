package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sporting.goods.rental.entities.Items;
import ru.sporting.goods.rental.entities.TypeOfItem;

import java.util.List;

public interface TypeOfItemRepository extends JpaRepository<TypeOfItem, Long> {
}
