package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.TypeOfItem;

public interface ProductTypeRepository extends JpaRepository<TypeOfItem, Long> {
}
