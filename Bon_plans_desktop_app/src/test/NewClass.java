/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.entity.EnumGroupe;

/**
 *
 * @author touir
 */
public class NewClass extends Application{
    
    public static void main(String[] args){
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        IPlan planService = new PlanImpl();
        
        Plan plan = planService.selectAll().get(0);
        CurrentSession.addData("openedPlan", plan);
        
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_OUTSIDER);
        
    }
}
