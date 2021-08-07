package com.stage.livraison.payload.Request;


import javax.validation.constraints.NotBlank;

public class ColisUpReq {

    @NotBlank
    private String titre;
    @NotBlank
    private String statut;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
