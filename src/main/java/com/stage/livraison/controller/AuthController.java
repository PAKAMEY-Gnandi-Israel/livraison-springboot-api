package com.stage.livraison.controller;

import com.stage.livraison.Service.UserDetailsImpl;
import com.stage.livraison.entity.ERole;
import com.stage.livraison.entity.Role;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.filter.JwtUtils;
import com.stage.livraison.payload.Request.LoginRequest;
import com.stage.livraison.payload.Request.SignupRequest;
import com.stage.livraison.payload.Response.JwtResponse;
import com.stage.livraison.payload.Response.MessageResponse;
import com.stage.livraison.repository.RoleRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/connexion")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        if ( userDetails.getActif() !=1) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Votre compte a été désactivé!"));
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getNom(),
                userDetails.getPrenom(),
                userDetails.getEmail(),
                userDetails.getAdresse(),
                userDetails.getPassword(),
                userDetails.getSituation_pro(),
                userDetails.getSexe(),
                userDetails.getActif(),
                userDetails.getBirthday(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Utilisateur user = new Utilisateur(signUpRequest.getNom(),signUpRequest.getPrenom(),signUpRequest.getEmail(),signUpRequest.getAdresse(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getSituation_pro(),signUpRequest.getSexe(),signUpRequest.getActif(),signUpRequest.getBirthday() , signUpRequest.getNum_tel()
      );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("L'utilisateur a été enrégistré avec succès"));
    }
}