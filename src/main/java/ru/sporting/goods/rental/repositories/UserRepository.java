package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
