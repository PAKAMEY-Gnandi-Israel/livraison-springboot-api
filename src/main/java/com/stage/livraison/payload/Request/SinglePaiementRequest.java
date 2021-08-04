package com.stage.livraison.payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SinglePaiementRequest {
    @NotBlank
    private int montant;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String titre;

    public int getMontant() {
        return montant;
    }

    public String getEmail() {
        return email;
    }

    public String getTitre() {
        return titre;
    }
}
