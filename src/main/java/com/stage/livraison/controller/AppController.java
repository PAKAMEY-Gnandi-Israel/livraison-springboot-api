package com.stage.livraison.controller;

import com.stage.livraison.Service.ColisService;
import com.stage.livraison.Service.UserDetailsImpl;
import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.payload.Request.ColisRequest;
import com.stage.livraison.payload.Request.LivRequest;
import com.stage.livraison.payload.Request.SignupRequest;
import com.stage.livraison.payload.Response.MessageResponse;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/test")
public class AppController {
    @Autowired
    private ColisService colisService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private MissionRepository missionRepository;
    private UserDetailsImpl userDetails;
    Authentication authentication;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @PostMapping("/saveLivraison")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
   public ResponseEntity saveLivraison (@Valid @RequestBody LivRequest livRequest) {

        Utilisateur livreur = utilisateurRepository.getUserByEmail(livRequest.getEmail());
      Colis colis = missionRepository.getColisByTitre(livRequest.getTitre());

      colis.setLivreur(livreur);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/saveColis")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity saveColis(@Valid @RequestBody ColisRequest colisRequest)
    {

        Utilisateur cli = utilisateurRepository.getUserByEmail(colisRequest.getEmail());
        colisService.saveProductToDB(colisRequest.getPrix(),colisRequest.getTitre(),colisRequest.getDescription(),colisRequest.getAdresse_recup(),colisRequest.getAdresse_liv(),colisRequest.getCode_sec(),colisRequest.getStatut(),colisRequest.getLongueur(),colisRequest.getLargeur(),colisRequest.getHauteur(),colisRequest.getPoids(),colisRequest.getImage_av(),colisRequest.getImage_ap(),colisRequest.getDate_echeance(),cli);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping(value = "/userColis/{utilisateur_id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<Colis> afficherLesColisDeUser(@PathVariable int utilisateur_id) {
        return missionRepository.findColisByUserId(utilisateur_id);
    }

    @GetMapping(value = "/notUserColis/{utilisateur_id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<Colis> afficherLesColis(@PathVariable int utilisateur_id) {
        return missionRepository.findColisExceptUserId(utilisateur_id);
    }

    @GetMapping(value = "/livreurColis/{livreur_id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<Colis> afficherMissionsLivreur(@PathVariable int livreur_id) {
        return missionRepository.findColisByLivreurId(livreur_id);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }



    @PutMapping
    public ResponseEntity updateUser(@RequestBody Utilisateur utilisateur){
        Utilisateur savedUser = utilisateurRepository.findByEmail((utilisateur.getEmail()))
                .orElseThrow(() -> new RuntimeException(
                        String.format("ne peut pas trouver ce utilisateur par son email %S",utilisateur.getEmail())));
        savedUser.setAdresse(utilisateur.getAdresse());
        savedUser.setSituation_pro(utilisateur.getSituation_pro());
        savedUser.setNum_tel(utilisateur.getNum_tel());
        savedUser.setEmail(utilisateur.getAdresse());
        savedUser.setPassword(utilisateur.getPassword());

        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/updateColisStatus", method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity updateColisStatus(@RequestBody String titre, @RequestBody String statut){
        Colis savedcolis = missionRepository.getColisByTitre((titre));

        savedcolis.setStatut(statut);


        missionRepository.save(savedcolis);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(path = "/getColisStatus", method = RequestMethod.GET)
    @PutMapping
    public ResponseEntity<?>  getColisStatus(@RequestBody String titre){
        Colis savedcolis = missionRepository.getColisByTitre((titre));
String statut =  savedcolis.getStatut();

        return ResponseEntity.ok(new MessageResponse(statut));
    }


    @GetMapping("/Colis")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Colis> getAllColis() {
        return missionRepository.findAll();
    }
    @GetMapping("/ColisLivré")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Colis> getAllColisDelivered() {
        return missionRepository.getColisLivré();
    }

    @GetMapping("/User")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
    }
}
