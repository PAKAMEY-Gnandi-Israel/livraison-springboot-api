package com.stage.livraison.payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LivRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String titre;

    public String getEmail() {
        return email;
    }

    public String getTitre() {
        return titre;
    }
}
