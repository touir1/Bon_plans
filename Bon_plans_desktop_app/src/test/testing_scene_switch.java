/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.stage.Stage;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.entity.EnumGroupe;

/**
 *
 * @author touir
 */
public class testing_scene_switch extends Application{
    
    
    
    public static void main(String[] args){
        
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        IUtilisateur utilisateurService = new UtilisateurImpl();
        CurrentSession.setUtilisateur(utilisateurService.findOne("idGroupe", EnumGroupe.Client.getValue()).get(0));
        
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.HistoriqueRESERVATIONAnnonceur);
        
    }
}
