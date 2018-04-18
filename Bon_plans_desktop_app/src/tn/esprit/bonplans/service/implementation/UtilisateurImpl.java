/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.util.List;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
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
    public Utilisateur seConnecter(String email, String mpd){
        List<Utilisateur> utilisateurs = findOne("email", email);
        if (utilisateurs.isEmpty()){
            return null;
        }
        if (!utilisateurs.get(0).getMdp().equals(mpd)){
            return null;
        }
        return utilisateurs.get(0);
    }
    
}
