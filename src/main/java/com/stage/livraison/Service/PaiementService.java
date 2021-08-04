package com.stage.livraison.Service;

import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Paiement;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaiementService {
    @Autowired
    PaiementRepository paiementRepository;
    public void  savePaiementToDB(int montant , Utilisateur createur , Colis colis)
    {

        Paiement p = new Paiement();
        /*
        String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        if(fileName1.contains("..") ||fileName2.contains("..") )
        {
            System.out.println("Fichiers non valides");
        }
        try {
            c.setImg_av_emball(Base64.getEncoder().encodeToString(file1.getBytes()));
            c.setImg_ap_emball(Base64.getEncoder().encodeToString(file2.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        p.setMontant(montant);
        p.setClient(createur);
        paiementRepository.save(p);
    }
}
