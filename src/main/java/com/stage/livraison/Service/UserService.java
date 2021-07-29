package com.stage.livraison.Service;
import com.stage.livraison.repository.MissionRepository;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service("UserService")
public class UserService {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

}