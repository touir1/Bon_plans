/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.tests;

import artan.entities.Plan;
import artan.services.PlanServices;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Mohamed Ali
 */
public class Test {
    public static void main(String args[]){
        
        LocalDate date = java.time.LocalDate.now();
        Plan plan = new Plan(17, "test", "java", "url photo", 15.00, 12.00, 100, java.sql.Date.valueOf(date),java.sql.Date.valueOf(date), 100, 0, 33, 11);
        PlanServices planservices = new PlanServices();
        
        //planservices.ajouterPlan(plan);
        //planservices.supprimerPlan(19);
        //planservices.modifierPlan(plan);
        //System.out.println(planservices.listePlan());
        //System.out.println(planservices.rechercheParID(17));
        //System.out.println(planservices.rechercheParUtilisateur(33));
        //System.out.println(planservices.rechercheParCategorie(11));
        //planservices.changerStatut(14, 0);
        
    }
}
