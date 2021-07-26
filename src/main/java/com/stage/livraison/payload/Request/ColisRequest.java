package com.stage.livraison.payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ColisRequest {
    @NotBlank

    private  Double prix;
    @NotBlank

    private  String titre;
    @NotBlank

    @Email
    private String description;
    @NotBlank

    private  String code_sec;
    @NotBlank

    private String adresse_recup;
    @NotBlank

    private  String adresse_liv;
    @NotBlank

    private  String statut;
    @NotBlank

    private  Double longueur;
    @NotBlank

    private  Double largeur;
    @NotBlank

    private  Double hauteur;
    @NotBlank

    private  Double poids;

    @NotBlank
    private String image_av;
    @NotBlank
    private String image_ap;
    @NotBlank
    private String date_echeance;
    @NotBlank
    private String email;

    public Double getPrix() {
        return prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getCode_sec() {
        return code_sec;
    }

    public String getAdresse_recup() {
        return adresse_recup;
    }

    public String getAdresse_liv() {
        return adresse_liv;
    }

    public String getStatut() {
        return statut;
    }

    public Double getLongueur() {
        return longueur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public Double getPoids() {
        return poids;
    }

    public String getImage_av() {
        return image_av;
    }

    public String getImage_ap() {
        return image_ap;
    }

    public String getDate_echeance() {
        return date_echeance;
    }

    public String getEmail() {
        return email;
    }
}
