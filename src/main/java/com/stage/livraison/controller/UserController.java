package com.stage.livraison.controller;

import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.payload.Request.UpdateUserRequest;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity updateUser(@RequestBody UpdateUserRequest up){
        Utilisateur savedUser = utilisateurRepository.findByEmail((up.getEmail()))
                .orElseThrow(() -> new RuntimeException(
                        String.format("ne peut pas trouver ce utilisateur par son email %S",up.getEmail())));
        savedUser.setNom(up.getNom());
        savedUser.setPrenom(up.getPrenom());
        savedUser.setEmail(up.getEmail());

        utilisateurRepository.save(savedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allUser")
    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
    }
}

