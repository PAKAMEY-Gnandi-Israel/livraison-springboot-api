package com.stage.livraison.repository;
import com.stage.livraison.entity.ERole;
import com.stage.livraison.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
