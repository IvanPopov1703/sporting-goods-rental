package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.TestEmpty;

public interface TestEmptyRepository extends JpaRepository<TestEmpty, Long> {
}
