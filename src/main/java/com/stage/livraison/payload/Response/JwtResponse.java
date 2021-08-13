package com.stage.livraison.payload.Response;

import java.sql.Date;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private  String nom;
    private  String prenom;
    private String email;
    private  String adresse;
    private String password;
    private  String situation_pro;
    private  String sexe;
    private int actif= 0;
    private String birthday;
    private String enginU;
    private String img_carte;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String nom, String prenom, String email, String adresse, String password, String situation_pro, String sexe, int actif, String birthday,String enginU,String img_carte, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
        this.situation_pro = situation_pro;
        this.sexe = sexe;
        this.actif = actif;
        this.birthday = birthday;
        this.enginU = enginU;
        this.img_carte = img_carte;
        this.roles = roles;

    }

    public String getImg_carte() {
        return img_carte;
    }

    public void setImg_carte(String img_carte) {
        this.img_carte = img_carte;
    }

    public String getEnginU() {
        return enginU;
    }

    public void setEnginU(String enginU) {
        this.enginU = enginU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSituation_pro() {
        return situation_pro;
    }

    public void setSituation_pro(String situation_pro) {
        this.situation_pro = situation_pro;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
