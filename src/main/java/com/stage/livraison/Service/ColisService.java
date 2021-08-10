package com.stage.livraison.Service;

import com.stage.livraison.dto.ColisDto;
import com.stage.livraison.entity.Colis;
import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColisService {
    @Autowired
    private MissionRepository missionRepository;

    public void  saveProductToDB(Double prix , String titre, String description,  String adresse_recup,String adresse_liv,String code_sec, String statut,String engin, Double longueur , Double largeur , Double hauteur, Double poids,String file1, String file2,String date_echeance, Utilisateur client )
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
        c.setEngin(engin);
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


    public List<ColisDto> getUserColis( Long id)
    {


        //Get all categories from repositories
        List <Colis> colis=  missionRepository.findColisByUserId(id);

        //map bdCategories to Categories dto

        List<ColisDto> lCatDto = colisToColisDtoMapper(colis);


        //return Categories dto
        return  lCatDto;

    }

    public List<ColisDto> getLivreurColis( Long id)
    {


        //Get all categories from repositories
        List <Colis> colis=  missionRepository.findColisByLivreurId(id);

        //map bdCategories to Categories dto

        List<ColisDto> lCatDto = colisToColisDtoMapper(colis);


        //return Categories dto
        return  lCatDto;

    }

    public List<ColisDto> getNotUserColis( Long id)
    {


        //Get all categories from repositories
        List <Colis> colis=  missionRepository.findColisExceptUserId(id);

        //map bdCategories to Categories dto

        List<ColisDto> lCatDto = colisToColisDtoMapper(colis);


        //return Categories dto
        return  lCatDto;

    }
    private List<ColisDto> colisToColisDtoMapper(List<Colis> lesColis){
        List<ColisDto> listColisDto = new ArrayList<>();

        for (Colis colis: lesColis)  {

            ColisDto categoryDto = new ColisDto();

            categoryDto.setId(colis.getId());
            categoryDto.setPrix(colis.getPrix());
            categoryDto.setTitre(colis.getTitre());
            categoryDto.setAdresse_liv(colis.getAdresse_liv());
            categoryDto.setAdresse_recup(colis.getAdresse_recup());
            categoryDto.setCode_sec(colis.getCode_sec());
            categoryDto.setDate_echeance(colis.getDate_echeance());
            categoryDto.setStatut(colis.getStatut());
            categoryDto.setLongueur(colis.getLongueur());
            categoryDto.setLargeur(colis.getLargeur());
            categoryDto.setHauteur(colis.getHauteur());
            categoryDto.setPoids(colis.getPoids());
            categoryDto.setImage_av(colis.getImg_av_emball());
            categoryDto.setImage_ap(colis.getImg_ap_emball());
            categoryDto.setDescription(colis.getDescription());
            listColisDto.add(categoryDto);

        }
        return listColisDto;
    }


}
