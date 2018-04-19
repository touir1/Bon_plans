/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import tn.esprit.bonplans.entity.Utilisateur;
import utils.service.GenericServiceInterface;

/**
 *
 * @author KC
 */
public interface IUtilisateur extends GenericServiceInterface<Utilisateur> {
    public Utilisateur connecter(String email, String mdp);
    public Utilisateur inscrire(String email, String nom, String prenom, String mdp);
}
