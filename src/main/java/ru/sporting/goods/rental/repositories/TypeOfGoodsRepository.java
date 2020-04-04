package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.model.TypeOfGoods;

public interface TypeOfGoodsRepository extends JpaRepository<TypeOfGoods, Long> {
}
