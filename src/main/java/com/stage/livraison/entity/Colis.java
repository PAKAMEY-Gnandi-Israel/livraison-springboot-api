package com.stage.livraison.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonSerialize
@Entity
@Table(name="colis", uniqueConstraints = {
        @UniqueConstraint(columnNames = "titre")
})
public class Colis extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false )
    private Double prix;
    @Column(nullable = false, unique = true)
    private String titre;
    @Column(nullable = false )
    private String description;
    @Column(nullable = false)
    private String adresse_recup;
    @Column(nullable = false)
    private String adresse_liv;
    @Column(nullable = false )
    private String code_sec;
    @Column(nullable = false )
    private String statut;

    @Column(nullable = false)
    private double longueur;
    @Column(nullable = false )
    private double hauteur;
    @Column(nullable = false )
    private double largeur;
    @Column(nullable = false )
    private double poids;



    @Column(columnDefinition = "TEXT",nullable = false )
    private String img_av_emball;
    @Column(columnDefinition = "TEXT",nullable = false )
    private String Img_ap_emball;
    @Column(nullable = false )
    private String date_echeance;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Utilisateur client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livreur_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utilisateur livreur;
    public Colis() {
    }

    public Colis(Long id, Double prix, String titre, String description, String adresse_recup, String adresse_liv, String code_sec, String statut, double longueur, double largeur, double poids, double hauteur, String img_av_emball, String img_ap_emball, String date_echeance, Utilisateur client) {
        this.id = id;
        this.prix = prix;
        this.titre = titre;
        this.description = description;
        this.adresse_recup = adresse_recup;
        this.adresse_liv = adresse_liv;
        this.code_sec = code_sec;
        this.statut = statut;
        this.longueur = longueur;
        this.largeur = largeur;
        this.poids = poids;
        this.hauteur = hauteur;
        this.img_av_emball = img_av_emball;
        Img_ap_emball = img_ap_emball;
        this.date_echeance = date_echeance;
        this.client = client;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public String getImg_av_emball() {
        return img_av_emball;
    }

    public void setImg_av_emball(String img_av_emball) {
        this.img_av_emball = img_av_emball;
    }

    public String getImg_ap_emball() {
        return Img_ap_emball;
    }

    public void setImg_ap_emball(String img_ap_emball) {
        Img_ap_emball = img_ap_emball;
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

    public String getAdresse_liv() {
        return adresse_liv;
    }

    public void setAdresse_liv(String adresse_liv) {
        this.adresse_liv = adresse_liv;
    }

    public String getCode_sec() {
        return code_sec;
    }

    public void setCode_sec(String code_sec) {
        this.code_sec = code_sec;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }



    public String getAdresse_recup() {
        return adresse_recup;
    }

    public void setAdresse_recup(String adresse_recup) {
        this.adresse_recup = adresse_recup;
    }

    public String getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(String date_echeance) {
        this.date_echeance = date_echeance;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Utilisateur getLivreur() {
        return livreur;
    }

    public void setLivreur(Utilisateur livreur) {
        this.livreur = livreur;
    }

}
