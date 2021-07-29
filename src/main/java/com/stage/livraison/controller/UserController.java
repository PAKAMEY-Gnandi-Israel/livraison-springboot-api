package com.stage.livraison.controller;

import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateur")
public class UserController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private MissionRepository missionRepository;

    @GetMapping(value = "/getOneUser/{id}")
    public Optional<Utilisateur> afficherutilisateur(@PathVariable Long id) {
        return utilisateurRepository.findById(id);
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

    @GetMapping("/allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
    }
}

