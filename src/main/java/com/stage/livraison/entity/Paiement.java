package com.stage.livraison.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name ="Paiement" ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Paiement  extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false )
    private int montant;
    @Column(nullable = false )
    private String typep;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Utilisateur client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colis_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Colis colis;

    public Paiement() {
    }

    public Paiement(Long id, int montant,String typep, Utilisateur client, Colis colis) {
        this.id = id;
        this.montant = montant;
        this.typep = typep;
        this.client = client;
        this.colis = colis;
    }

    public String getTypep() {
        return typep;
    }

    public void setTypep(String typep) {
        this.typep = typep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }
}
