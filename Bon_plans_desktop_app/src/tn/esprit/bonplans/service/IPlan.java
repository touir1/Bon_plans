/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.util.List;
import utils.service.GenericServiceInterface;
import tn.esprit.bonplans.entity.Plan;
import utils.service.ServiceResponse;

/**
 *
 * @author SadfiAmine
 */
public interface IPlan  extends GenericServiceInterface<Plan>{
    
    public List<Plan> getListOfNonValidatedPlans(ServiceResponse serviceResponse);
    public List<Plan> getListOfModifiedPlans(ServiceResponse serviceResponse);
    public void validerPlan(Plan plan, ServiceResponse serviceResponse);
    public void refuserPlan(Plan plan, ServiceResponse serviceResponse);
}
