package com.stage.livraison.repository;

import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Role;
import com.stage.livraison.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional <Utilisateur >findByEmail(String email);
    Boolean existsByNom(String nom);
    Boolean existsByEmail(String email);

    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    public Utilisateur getUserByEmail(@Param("email") String email);

}
