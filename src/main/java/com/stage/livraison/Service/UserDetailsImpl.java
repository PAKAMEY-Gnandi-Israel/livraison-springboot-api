package com.stage.livraison.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.livraison.entity.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private Long id;

    private  String nom;

    private  String prenom;

    private  String email;

    private  String adresse;
    @JsonIgnore
    private  String password;

    private  String situation_pro;

    private  String sexe;

    private int actif= 0;

    private String birthday;
    private int num_tel;
    private String enginU;
    private String img_carte;
    private Collection<? extends  GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String nom, String prenom, String email, String adresse, String password, String situation_pro, String sexe, int actif, String birthday, int num_tel,String enginU,String img_carte,Collection<? extends GrantedAuthority> authorities) {
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
        this.authorities = authorities;
        this.num_tel =  num_tel;
        this.enginU = enginU;
        this.img_carte = img_carte;
    }

    public static UserDetailsImpl build(Utilisateur user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getAdresse(),
                user.getPassword(),
                user.getSituation_pro(),
                user.getSexe(),
                user.getActif(),
                user.getBirthDay(),
                user.getNum_tel(),
                user.getEnginU(),
                user.getImg_carte(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return  authorities ;
    }
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSituation_pro() {
        return situation_pro;
    }

    public String getSexe() {
        return sexe;
    }

    public int getActif() {
        return actif;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getImg_carte() {
        return img_carte;
    }

    public String getEnginU() {
        return enginU;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

