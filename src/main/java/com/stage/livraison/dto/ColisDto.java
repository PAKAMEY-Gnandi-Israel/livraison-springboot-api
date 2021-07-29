package com.stage.livraison.dto;


public class ColisDto {
    private Long id;


    private  Double prix;

    private  String titre;



    private String description;


    private  String code_sec;

    private String adresse_recup;


    private  String adresse_liv;


    private  String statut;

    private  Double longueur;

    private  Double largeur;

    private  Double hauteur;

    private  Double poids;

    private String image_av;
    private String image_ap;
    private String date_echeance;


    public ColisDto() {
    }

    public ColisDto(Long id, Double prix, String titre, String description, String code_sec, String adresse_recup, String adresse_liv, String statut, Double longueur, Double largeur, Double hauteur, Double poids, String image_av, String image_ap, String date_echeance) {
        this.id = id;
        this.prix = prix;
        this.titre = titre;
        this.description = description;
        this.code_sec = code_sec;
        this.adresse_recup = adresse_recup;
        this.adresse_liv = adresse_liv;
        this.statut = statut;
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.poids = poids;
        this.image_av = image_av;
        this.image_ap = image_ap;
        this.date_echeance = date_echeance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode_sec() {
        return code_sec;
    }

    public void setCode_sec(String code_sec) {
        this.code_sec = code_sec;
    }

    public String getAdresse_recup() {
        return adresse_recup;
    }

    public void setAdresse_recup(String adresse_recup) {
        this.adresse_recup = adresse_recup;
    }

    public String getAdresse_liv() {
        return adresse_liv;
    }

    public void setAdresse_liv(String adresse_liv) {
        this.adresse_liv = adresse_liv;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public String getImage_av() {
        return image_av;
    }

    public void setImage_av(String image_av) {
        this.image_av = image_av;
    }

    public String getImage_ap() {
        return image_ap;
    }

    public void setImage_ap(String image_ap) {
        this.image_ap = image_ap;
    }

    public String getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(String date_echeance) {
        this.date_echeance = date_echeance;
    }
}
