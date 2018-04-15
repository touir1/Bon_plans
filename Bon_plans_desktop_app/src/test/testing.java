/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import tn.esprit.bonplans.entity.Groupe;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IGroupe;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.GroupeImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;

/**
 *
 * @author touir
 */
public class testing {
    public static void main(String[] args){
        IUtilisateur utilisateurService = new UtilisateurImpl();
        IGroupe groupeService = new GroupeImpl();
        
        /*
        Groupe groupe = new Groupe();
        groupe.setDescription("ADMIN");
        
        groupeService.save(groupe);
        
        groupe.setDescription("SUPER ADMIN");
        
        groupeService.save(groupe);
        
        groupe.setDescription("CLIENT");
        
        groupeService.save(groupe);
        */
        Groupe clientGroup = groupeService.findOne("DESCRIPTION", "CLIENT").get(0);
        Groupe adminGroup = groupeService.findOne("DESCRIPTION", "ADMIN").get(0);
        Groupe superAdminGroup = groupeService.findOne("DESCRIPTION", "SUPER ADMIN").get(0);
        
        
        Utilisateur utilisateur = new Utilisateur();
        
        utilisateur.setNom("Touir");
        utilisateur.setPrenom("Mohamed Ali");
        utilisateur.setAdresse("06 kawafel street rades");
        utilisateur.setEmail("touir.mat@gmail.com");
        utilisateur.setDateCreation(new Date());
        utilisateur.setIdGroupe(adminGroup.getIdGroupe());
        utilisateur.setMdp("12345678");
        utilisateur.setVille("Rades");
        
        utilisateurService.save(utilisateur);
        
        utilisateur.setNom("Touir");
        utilisateur.setPrenom("Eya");
        utilisateur.setAdresse("06 kawafel street rades");
        utilisateur.setEmail("eya.touir@gmail.com");
        utilisateur.setDateCreation(new Date());
        utilisateur.setIdGroupe(clientGroup.getIdGroupe());
        utilisateur.setMdp("12345678");
        utilisateur.setVille("Rades");
        
        utilisateurService.save(utilisateur);
        
        utilisateur.setNom("Super");
        utilisateur.setPrenom("Admin");
        utilisateur.setAdresse("inconnu");
        utilisateur.setEmail("admin.admin@gmail.com");
        utilisateur.setDateCreation(new Date());
        utilisateur.setIdGroupe(superAdminGroup.getIdGroupe());
        utilisateur.setMdp("12345678");
        utilisateur.setVille("inconnu");
        
        utilisateurService.save(utilisateur);
    }
}
