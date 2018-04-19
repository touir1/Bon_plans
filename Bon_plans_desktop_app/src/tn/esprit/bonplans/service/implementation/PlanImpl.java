/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.util.List;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.IPlan;
import utils.entity.EnumValidation;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class PlanImpl extends GenericServiceImplementation<Plan> implements IPlan{

    public PlanImpl(){
        super(Plan.class);
    }

    @Override
    public List<Plan> getListOfNonValidatedPlans() {
        return findOne("statut",EnumValidation.EnAttentedeValidation.getValue());
    }
    
    @Override
    public List<Plan> getListOfModifiedPlans() {
        return findOne("statut",EnumValidation.modifiee.getValue());
    }

    @Override
    public void validerPlan(Plan plan) {
        plan.setStatut(EnumValidation.validee.getValue());
        update(plan);
    }
    
    @Override
    public void refuserPlan(Plan plan) {
        plan.setStatut(EnumValidation.refusée.getValue());
        update(plan);
    }
}
