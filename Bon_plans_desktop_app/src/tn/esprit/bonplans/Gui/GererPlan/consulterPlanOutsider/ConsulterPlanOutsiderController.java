/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererPlan.consulterPlanOutsider;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import utils.Converter;
import utils.CurrentSession;
import utils.Dialog;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ConsulterPlanOutsiderController extends Application implements Initializable {
    
    private ICategorie categorieService;
    
    private Plan openedPlan;
    
    @FXML
    private AnchorPane Menu;
    @FXML
    private Label title;
    @FXML
    private ImageView imagePlan;
    @FXML
    private TextArea description;
    @FXML
    private Label initialPrice;
    @FXML
    private Label promoPrice;
    @FXML
    private Label promoPercentage;
    @FXML
    private Label units;
    @FXML
    private Label beginDate;
    @FXML
    private Label endDate;
    @FXML
    private Label categorie;
    @FXML
    private JFXButton btnReservation;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.categorieService = new CategorieImpl();
        
        //get plan opened
        this.openedPlan = (Plan) CurrentSession.getData("openedPlan");
        if(openedPlan != null){
            title.setText(openedPlan.getTitre());
            description.setText(openedPlan.getDescription());
            initialPrice.setText(openedPlan.getPrixInitial()+" TND");
            promoPrice.setText(openedPlan.getPrixPromo()+" TND");
            promoPercentage.setText(Math.round(100*openedPlan.getPrixPromo()/openedPlan.getPrixInitial())+"%");
            units.setText(Integer.toString(openedPlan.getNbPlaceDispo()));
            beginDate.setText(Converter.convertDateToString(openedPlan.getDateDebut(), "dd/MM/yyyy"));
            endDate.setText(Converter.convertDateToString(openedPlan.getDateFin(), "dd/MM/yyyy"));
            try{
                Image image = new Image(openedPlan.getUrlPhoto());
                imagePlan.setImage(image);
            }
            catch(Exception e){
                //do nothing
            }
            Categorie categorieFromDatabase = categorieService.getByID(openedPlan.getIdCategorie());
            if(categorieFromDatabase != null) {
                categorie.setText(categorieFromDatabase.getTitre());
            }
            else{
                categorie.setText("");
            }
        }
    }    

    @FXML
    private void openAccueil(MouseEvent event) {
        SceneHandler.openScene(SceneEnum.ACCEUIL);
    }

    @FXML
    private void openSeConnecter(MouseEvent event) {
        SceneHandler.openScene(SceneEnum.SE_CONNECTER);
    }

    @FXML
    private void openMeilleursPlans(MouseEvent event) {
        //SceneHandler.openScene(SceneEnum.MEILLEURS_PLANS);
    }

    @FXML
    private void openReservation(MouseEvent event) {
        Dialog.Error("Authentification", "Non authentifié", "Veuillez vous connecter pour pouvoir réserver.").showAndWait();
        SceneHandler.openScene(SceneEnum.SE_CONNECTER);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_OUTSIDER);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
