package com.stage.livraison.Service;

import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.payload.Request.LivRequest;
import com.stage.livraison.payload.Response.MessageResponse;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service("UserService")
public class UserService {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
/*
    public ResponseEntity<?> saveLivraison(String titre, Utilisateur livreur) {

        Colis colis = missionRepository.getColisByTitre(titre);
        if (colis.getStatut()== "livré") {
            colis.setLivreur(livreur);
            missionRepository.save(colis);
            return ResponseEntity.ok(new MessageResponse(colis.getStatut()));


        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Ce colis n'a pas encore été livré"));
    }*/
}