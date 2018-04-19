/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import utils.Encrypt;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author KC
 */
public class UtilisateurImpl extends GenericServiceImplementation<Utilisateur> implements IUtilisateur{

    public UtilisateurImpl() {
        super(Utilisateur.class);
    }
    
    @Override
    public Utilisateur seConnecter(String email, String mpd) {
        try {
            List<Utilisateur> utilisateurs = findOne("email", email);
            if (utilisateurs.isEmpty()) {
                return null;
            }
            if (!utilisateurs.get(0).getMdp().equals(Encrypt.sha1(mpd))) {
                return null;
            }
            return utilisateurs.get(0);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
}
