/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author KC
 */
public class UtilisateurImp extends GenericServiceImplementation<Utilisateur> implements IUtilisateur{

    public UtilisateurImp(Class<Utilisateur> myClass) {
        super(Utilisateur.class);
    }
    
    
}
