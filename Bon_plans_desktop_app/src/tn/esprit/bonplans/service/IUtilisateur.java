/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import tn.esprit.bonplans.entity.Utilisateur;
import utils.service.GenericServiceInterface;
import utils.Error;

/**
 *
 * @author KC
 */
public interface IUtilisateur extends GenericServiceInterface<Utilisateur> {
    public Utilisateur connecter(String email, String mdp, Error error);
    public Utilisateur inscrire(String email, String nom, String prenom, String mdp, Error error);
    public boolean isExist(String email);
    public void activerCompte(Utilisateur utilisateur);
    public void deactiverCompte(Utilisateur utilisateur);
    public Utilisateur getUtilisateurByEmail(String email);
    public void envoyerCodeActivation(Utilisateur utilisateur);
    public void updatePwd(Utilisateur utilisateur, String mdp);
    public Utilisateur updateUser(Utilisateur utilisateur, boolean withUpdatePwd);
}
