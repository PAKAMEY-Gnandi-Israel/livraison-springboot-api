package com.stage.livraison.controller;

import com.stage.livraison.Service.ColisService;
import com.stage.livraison.Service.PaiementService;
import com.stage.livraison.Service.UserDetailsImpl;
import com.stage.livraison.Service.UserService;
import com.stage.livraison.dto.ColisDto;
import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Paiement;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.payload.Request.ColisRequest;
import com.stage.livraison.payload.Request.ColisUpReq;
import com.stage.livraison.payload.Request.LivRequest;
import com.stage.livraison.payload.Request.SinglePaiementRequest;
import com.stage.livraison.payload.Response.MessageResponse;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.PaiementRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/livraison")
public class AppController {
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private ColisService colisService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private MissionRepository missionRepository;
    private UserDetailsImpl userDetails;
    Authentication authentication;


    @RequestMapping(path = "/updateColisLivreur", method = RequestMethod.PUT)
    @PutMapping
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
   public ResponseEntity updateColisLivreur (@Valid @RequestBody LivRequest livRequest) {
        Utilisateur livreur = utilisateurRepository.getUserByEmail(livRequest.getEmail());
        Colis colis = missionRepository.getColisByTitre(livRequest.getTitre() );

        if (colis.getStatut().equals("terminée") ) {
            System.out.println("success");
            colis.setLivreur(livreur);
            missionRepository.save(colis);
            return ResponseEntity.ok(new MessageResponse(colis.getStatut()));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Ce colis n'a pas encore été livré"));

    }
    @PostMapping("/saveColis")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity saveColis(@Valid @RequestBody ColisRequest colisRequest)
    {

        Utilisateur cli = utilisateurRepository.getUserByEmail(colisRequest.getEmail());
        colisService.saveProductToDB(colisRequest.getPrix(),colisRequest.getTitre(),colisRequest.getDescription(),colisRequest.getAdresse_recup(),colisRequest.getAdresse_liv(),colisRequest.getCode_sec(),colisRequest.getStatut(),colisRequest.getLongueur(),colisRequest.getLargeur(),colisRequest.getHauteur(),colisRequest.getPoids(),colisRequest.getImage_av(),colisRequest.getImage_ap(),colisRequest.getDate_echeance(),cli);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/savePaiement")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity savePaiement(@Valid @RequestBody SinglePaiementRequest singlep)
    {

        Utilisateur cli = utilisateurRepository.getUserByEmail(singlep.getEmail());
        Colis colis = missionRepository.getColisByTitre(singlep.getTitre() );
       paiementService.savePaiementToDB(singlep.getMontant(),cli,colis);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping(value = "/userColis/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List <ColisDto> afficherLesColisDeUser(@PathVariable Long id) {
        return colisService.getUserColis(id);
    }

    @GetMapping(value = "/getOneColis/{titre}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public Colis recupColis (@PathVariable String titre) {
        return missionRepository.getColisByTitre(titre);
    }


    @GetMapping(value = "/notUserColis/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<ColisDto> afficherLesColis(@PathVariable Long id) {
        return colisService.getNotUserColis(id);
    }

    @GetMapping(value = "/livreurColis/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<ColisDto> afficherMissionsLivreur(@PathVariable Long id) {
        return colisService.getLivreurColis(id);
    }



    @RequestMapping(path = "/updateColisStatus", method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity updateColisStatus(@Valid @RequestBody ColisUpReq cq){
        Colis savedcolis = missionRepository.getColisByTitre((cq.getTitre()));

        savedcolis.setStatut(cq.getStatut());


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
        return colisService.getAllColis();
    }
    @GetMapping("/ColisLivré")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Colis> getAllColisDelivered() {
        return missionRepository.getColisLivré();
    }
    @GetMapping("/allP")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Paiement> getAllPaiement() {
        return paiementRepository.findAll();
    }

}
