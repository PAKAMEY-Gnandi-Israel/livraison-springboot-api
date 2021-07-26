package com.stage.livraison.controller;

import com.stage.livraison.Service.ColisService;
import com.stage.livraison.Service.UserDetailsImpl;
import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
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
   public ResponseEntity saveLivraison (@PathVariable String titre) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Utilisateur livreur = utilisateurRepository.getUserByEmail(userPrincipal.getEmail());
      Colis colis = missionRepository.getColisByTitre(titre);

      colis.setLivreur(livreur);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/saveColis")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity saveColis(@RequestBody Double prix,
                                    @RequestBody  String titre,
                                    @RequestBody  String description,
                                    @RequestBody  String code_sec,
                                    @RequestBody  String adresse_recup,
                                    @RequestBody  String adresse_liv,
                                    @RequestBody  String statut,
                                    @RequestBody  Double longueur,
                                    @RequestBody  Double largeur,
                                    @RequestBody  Double hauteur,
                                    @RequestBody  Double poids,
                                    @RequestBody String image_av,
                                    @RequestBody String image_ap,
                                    @RequestBody  String date_echeance

                         )



    {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Utilisateur cli = utilisateurRepository.getUserByEmail(userPrincipal.getEmail());
        colisService.saveProductToDB(prix,titre,description,adresse_recup,adresse_liv,code_sec,statut,longueur,largeur,hauteur,poids,image_av,image_ap,date_echeance,cli);
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
