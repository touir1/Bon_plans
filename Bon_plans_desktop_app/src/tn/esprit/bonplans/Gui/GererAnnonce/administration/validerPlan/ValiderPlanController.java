/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validerPlan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderPlanController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(url);
        System.out.println(rb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ValiderPlan.fxml"));
        
        Scene Scene = new Scene(root);
        primaryStage.getIcons().add(new Image("http://localhost/bon_plans_api/uploads/Logo.png"));
        primaryStage.setScene(Scene);
        primaryStage.show();
        primaryStage.setTitle("Validation plans");
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
