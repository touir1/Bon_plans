/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.Iplan;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class PlanImpl extends GenericServiceImplementation<Plan> implements Iplan{

    public PlanImpl(){
        super(Plan.class);
    }
}
