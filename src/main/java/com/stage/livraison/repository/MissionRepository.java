package com.stage.livraison.repository;


import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Colis, Long> {

    List<Colis> findById(int id);

    @Query("SELECT c FROM Colis c WHERE c.titre = :titre and c.statut ='livré'")
    public Colis getColisByTitre(@Param("titre") String titre);


    @Query("SELECT c FROM Colis c WHERE c.client= :utilisateur_id ")
    List<Colis> findColisByUserId(@Param("utilisateur_id ") int utilisateur_id );

    @Query("SELECT c FROM Colis c WHERE c.livreur = :livreur_id ")
    List<Colis> findColisByLivreurId(@Param("livreur_id ") int livreur_id );

  /*  @Query("select * From mission m WHERE m.utilisateur_id = ?1")
    List<Colis> getUserMisson(int id);

    @Query("select * From mission m WHERE m.adresse = ?1")
    List<Colis> getMissionByAdresse(String adresse);


    @Query("select * From mission m WHERE m.statut = ?1")
    List<Colis> getFinishedMission(String statut);*/
}
