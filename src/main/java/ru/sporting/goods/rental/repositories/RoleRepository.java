package ru.sporting.goods.rental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sporting.goods.rental.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
