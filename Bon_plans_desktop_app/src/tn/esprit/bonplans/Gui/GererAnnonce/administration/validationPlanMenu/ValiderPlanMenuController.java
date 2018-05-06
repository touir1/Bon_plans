/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validationPlanMenu;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderPlanMenuController extends Application implements Initializable {
    @FXML
    private JFXButton btnValiderNewPlan;
    @FXML
    private JFXButton btnValiderModifiedPlan;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        SceneHandler.initTemplate(SceneEnum.VALIDER_PLAN_MENU);
    }    

    @FXML
    private void validerNewPlan(ActionEvent event) {
        
        SceneHandler.openScene(SceneEnum.VALIDER_NEW_PLAN);
    }

    @FXML
    private void validerModifiedPlan(ActionEvent event) {
        
        SceneHandler.openScene(SceneEnum.VALIDER_MODIFIED_PLAN);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.VALIDER_PLAN_MENU);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
