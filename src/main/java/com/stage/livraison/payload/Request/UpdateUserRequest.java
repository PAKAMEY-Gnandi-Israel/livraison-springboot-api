package com.stage.livraison.payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateUserRequest {

    @NotBlank

    private  String nom;
    @NotBlank

    private  String prenom;
    @NotBlank

    @Email
    private String email;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }
}
