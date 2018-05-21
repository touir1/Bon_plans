/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur;

/**
 *
 * @author KC
 */
public final class Constantes {
    
    public static final String ERR = "Erreur";
    public static final String INFO = "Info";
    
    // Contraites.
    public static final int MIN_LENGTH_PWD = 8;
    
    // Erreur inputs. 
    public static final String ERR_INPUT = "Entrée invalide";
    public static final String ERR_INPUT_EMAIL = "Email incorrect.";
    public static final String ERR_INPUT_PWD = "Mot de passe incorrect.";
    public static final String ERR_INPUT_NOM = "Nom incorrect.";
    public static final String ERR_INPUT_PRENOM = "Prénom incorrect.";
    public static final String ERR_INPUT_CODE_ACTIVATION = "Code d'activation incorrecte.";
    public static final String ERR_INPUT_EMAIL_PWD = "Email ou mot de passe incorrect.";
  
    // Autres.
    public static final String ERR_EMAIL_NONEXISTENT = "Email inexistant";
    public static final String TITLE_STAGE = "Bon plans";
    public static final String TITLE_ACTIVATION = "Activation du compte";
    
    // Contraites.
    public static final String ERR_CONST_PWD = "Votre mot de passe doit être composé de"
            + " " + MIN_LENGTH_PWD + " caractères au minimum.\nVeuillez introduire du nouveau votre mot de passe";
    
    // MSG.
    public static final String MSG_CONFIRMATION = "Nous vous avons envoyé un courriel de validation."
            + "\nPour poursuivre le processus de création du compte,"
            + " veuillez introduire le code d'activation.";
    public static final String MSG_ACTIVATION_INPUT = "Veuillez introduire votre code d'activation";
    public static final String MSG_ACTIVATION_WITH_MAIL = "Un code d'activation "
            + "à été envoyé à votre email.\nVeuillez introduire votre code d'activation";
    public static final String MSG_PWD_OUBLIE = "Veuillez introduire le nouveau mot de passe";
    public static final String MSG_SUCC_CHANGER_PWD = "Votre mot de passe a été modifié avec succès";
    public static final String MSG_SUCC_UPDATE = "Mise à jour terminée avec succès";
    public static final String MSG_SUCC_ADD_ADMIN = "Administrateur a été créé avec succes";
}
