package com.stage.livraison.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name ="Utilisateur" ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class Utilisateur extends AuditModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false )


    private  String nom;
    @Column(nullable = false)

    private  String prenom;
    @Column(nullable = false,unique = true)

    private  String email;
    @Column(nullable = false )

    private  String adresse;
    @Column(nullable = false )

    private  String password;
    @Column(nullable = false)

    private  String situation_pro;
    @Column(nullable = false)

    private  String sexe;
    @Column(nullable = false )
    private int actif= 1;
    @Column(nullable = true)
    private String birthDay;
    @Column(nullable = false)
    private  int num_tel;
    @Column(nullable = false )
    private  String enginU;


    @Column(columnDefinition = "TEXT",nullable = false )
    private String img_carte;

    @ManyToMany()
    @JoinTable(name="utilisateur_roles",
                     joinColumns = @JoinColumn(name = "utilisateur_id"),
   inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String adresse, String password, String situation_pro, String sexe, int actif, String birthDay, int num_tel,String enginU, String img_carte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
        this.situation_pro = situation_pro;
        this.sexe = sexe;
        this.actif = actif;
        this.birthDay = birthDay;
        this.num_tel = num_tel;
        this.enginU = enginU;
        this.img_carte = img_carte;
    }

    public String getImg_carte() {
        return img_carte;
    }

    public String getEnginU() {
        return enginU;
    }

    public void setEnginU(String enginU) {
        this.enginU = enginU;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
