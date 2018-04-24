/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.util.List;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import utils.Email;
import utils.entity.EnumValidation;
import utils.service.GenericServiceImplementation;
import utils.service.ServiceResponse;

/**
 *
 * @author SadfiAmine
 */
public class PlanImpl extends GenericServiceImplementation<Plan> implements IPlan{

    public PlanImpl(){
        super(Plan.class);
    }

    @Override
    public List<Plan> getListOfNonValidatedPlans(ServiceResponse serviceResponse) {
        return findOne("statut",EnumValidation.EnAttentedeValidation.getValue(),serviceResponse);
    }
    
    @Override
    public List<Plan> getListOfModifiedPlans(ServiceResponse serviceResponse) {
        return findOne("statut",EnumValidation.modifiee.getValue(),serviceResponse);
    }

    @Override
    public void validerPlan(Plan plan, ServiceResponse serviceResponse) {
        plan.setStatut(EnumValidation.validee.getValue());
        update(plan,serviceResponse);
        if(serviceResponse.isOk()){
            IUtilisateur utilisateurService = new UtilisateurImpl();
            Utilisateur utilisateur = utilisateurService.getByID(plan.getIdAnnonceur());
            Email.send(utilisateur.getEmail(), "[Bon Plans] Validation plan", 
                    "Votre plan ayant le titre '"+plan.getTitre()+"' a été validé par un modérateur.");
        }
    }
    
    @Override
    public void refuserPlan(Plan plan, ServiceResponse serviceResponse) {
        plan.setStatut(EnumValidation.refusée.getValue());
        update(plan,serviceResponse);
        if(serviceResponse.isOk()){
            IUtilisateur utilisateurService = new UtilisateurImpl();
            Utilisateur utilisateur = utilisateurService.getByID(plan.getIdAnnonceur());
            Email.send(utilisateur.getEmail(), "[Bon Plans] Validation plan", 
                    "Votre plan ayant le titre '"+plan.getTitre()+"' a été refusé par un modérateur.");
        }
    }
}
