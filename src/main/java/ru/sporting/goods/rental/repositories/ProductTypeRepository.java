package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
