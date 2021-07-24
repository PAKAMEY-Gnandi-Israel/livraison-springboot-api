package com.stage.livraison.Service;

import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ColisService {
    @Autowired
    private MissionRepository missionRepository;

    public void  saveProductToDB(Double prix , String titre, String description,  String adresse_recup,String adresse_liv,String code_sec, String statut, Double longueur , Double largeur , Double hauteur, Double poids, String file1, String file2,String date_echeance, Utilisateur client )
    {

        Colis c = new Colis();
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


        c.setPrix(prix);
        c.setTitre(titre);
        c.setAdresse_liv(adresse_liv);
        c.setAdresse_recup(adresse_recup);
        c.setCode_sec(code_sec);
        c.setDate_echeance(date_echeance);
        c.setStatut(statut);
        c.setLongueur(longueur);
        c.setLargeur(largeur);
        c.setHauteur(hauteur);
        c.setPoids(poids);
        c.setImg_av_emball(file1);
        c.setImg_ap_emball(file2);
        c.setClient(client);
        c.setDescription(description);
        missionRepository.save(c);
    }
    public List<Colis> getAllColis()
    {
        return missionRepository.findAll();
    }
    public void deleteColisById(Long id)
    {
        missionRepository.deleteById(id);
    }
    public void changeColisTitre(Long id ,String titre)
    {
        Colis c = new Colis();
        c= missionRepository.findById(id).get();
        c.setTitre(titre);
         missionRepository.save(c);
    }

}
