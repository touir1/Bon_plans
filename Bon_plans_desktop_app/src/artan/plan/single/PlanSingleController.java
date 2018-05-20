/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.plan.single;

import artan.entities.Plan;
import artan.plan.liste.PlanListeController;
import artan.services.PlanServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class PlanSingleController extends Application implements Initializable {
    
    @FXML
    private Text titre, quantite, description, prix;
    @FXML
    private ImageView planImage;
    
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PlanServices ps = new PlanServices();
        Plan plan = ps.rechercheParID(PlanListeController.identifiant);
        Image image = new Image(plan.getUrlPhoto());
        planImage.setImage(image);
        titre.setText(plan.getTitre());
        quantite.setText(plan.getQuantite() + " articles restant");
        description.setText(plan.getDescription());
        prix.setText(plan.getPrix() + " DNT");
    }    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.SINGLE_PLAN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
