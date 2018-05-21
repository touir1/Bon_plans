/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import utils.Email;
import utils.Encrypt;
import utils.entity.EnumGroupe;
import utils.service.GenericServiceImplementation;
import utils.service.ServiceResponse;
import utils.Error;
import utils.Other;

/**
 *
 * @author KC
 */
public class UtilisateurImpl extends GenericServiceImplementation<Utilisateur> implements IUtilisateur{

    private static final String ERR_USER_EXIST = "Email est déja utilisée par un autre utilisateur.";
    private static final String SUBJECT_EMAIL = "[BONPLANS] Activation de compte";
    private static final String TEXT_EMAIL = "Votre code d'activation est ";
    
    public UtilisateurImpl() {
        super(Utilisateur.class);
    }
    
    @Override
    public Utilisateur connecter(String email, String mpd, Error error) {
       try {
            Utilisateur utilisateur = getUtilisateurByEmail(email);
            if (utilisateur == null || !utilisateur.getMdp().equals(Encrypt.sha1(mpd))) {
                return null;
            }
            return utilisateur;
        } catch (Exception ex) {
            error.setMessage(ex.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur inscrire(String email, String nom, String prenom, String mdp, Error error) {
        Utilisateur utilisateur = null;
        if (isExist(email)){
            error.setMessage(ERR_USER_EXIST);
        }
        else {
            ServiceResponse serviceResponse = new ServiceResponse();
            try {
                utilisateur = new Utilisateur(EnumGroupe.Client.getValue(), Encrypt.sha1(mdp), email, nom, prenom, new Date(), Other.generateActivationCode());
                utilisateur = save(utilisateur, serviceResponse);
                if (!serviceResponse.isOk()) {
                    error.setMessage(serviceResponse.getExceptions().get(0).getMessage());
                }
                else {
                   envoyerCodeActivation(utilisateur);
                }
            } 
            catch (NoSuchAlgorithmException ex) {
                error.setMessage(ex.getMessage());
            }
        }       
        return utilisateur;
    }

    @Override
    public boolean isExist(String email) {
        return !findOne("email", email).isEmpty();
    }

    @Override
    public void activerCompte(Utilisateur utilisateur) {
        utilisateur.setIsActif(true);
        update(utilisateur);
    }
    
    @Override
    public void deactiverCompte(Utilisateur utilisateur){
        utilisateur.setIsActif(false);
        utilisateur.setCodeActivation(Other.generateActivationCode());
        update(utilisateur);
    }
    
    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        ServiceResponse serviceResponse = new ServiceResponse();
        List<Utilisateur> utilisateurs = findOne("email", email, serviceResponse);
        if (!serviceResponse.isOk() || utilisateurs.isEmpty()) {
            return null;
        }
        return utilisateurs.get(0);
    }
    
    @Override
    public void envoyerCodeActivation(Utilisateur utilisateur){
        Email.send(utilisateur.getEmail(), SUBJECT_EMAIL, TEXT_EMAIL + utilisateur.getCodeActivation());
    }
    
    @Override
    public void updatePwd(Utilisateur utilisateur, String mdp){
        try {
            utilisateur.setMdp(Encrypt.sha1(mdp));
            utilisateur.setIsActif(true);
            update(utilisateur);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Utilisateur updateUser(Utilisateur utilisateur, boolean withUpdatePwd){
        if (withUpdatePwd){
            try {
                utilisateur.setMdp(Encrypt.sha1(utilisateur.getMdp()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return update(utilisateur);
    }
}
