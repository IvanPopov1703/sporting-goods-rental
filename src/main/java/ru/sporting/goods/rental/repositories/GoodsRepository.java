package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.model.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
