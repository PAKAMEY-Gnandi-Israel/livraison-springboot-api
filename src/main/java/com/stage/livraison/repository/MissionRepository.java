package com.stage.livraison.repository;
import com.stage.livraison.entity.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Colis, Long> {

    List<Colis> findById(int id);


    @Query("SELECT c FROM Colis c WHERE c.titre = :titre")
    public Colis getColisByTitre(@Param("titre") String titre);

    @Query("SELECT c FROM Colis c WHERE c.statut ='livré'")
    public List<Colis> getColisLivré();


    @Query("SELECT c FROM Colis c WHERE c.client.id = ?1")
    public  List<Colis> findColisByUserId( Long id );




    @Query("SELECT c FROM Colis c WHERE c.client.id !=  ?1 ")
    List<Colis> findColisExceptUserId( Long id );


    @Query("SELECT c FROM Colis c WHERE c.livreur.id = ?1")
    public  List<Colis> findColisByLivreurId( Long id );


}
