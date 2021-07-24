package com.stage.livraison.Service;

import com.stage.livraison.entity.Utilisateur;
import com.stage.livraison.exceptions.EmailNotFoundException;
import com.stage.livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws EmailNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(s).
                orElseThrow(()-> new EmailNotFoundException("l'email fourni n'a pas été trouvé"));
        return UserDetailsImpl.build(utilisateur);
    }
}
