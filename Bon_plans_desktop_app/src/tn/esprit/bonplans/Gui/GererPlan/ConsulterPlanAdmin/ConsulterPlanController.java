/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererPlan.ConsulterPlanAdmin;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.Converter;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.service.ServiceResponse;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ConsulterPlanController extends Application implements Initializable {

    private static final String SERVICE_ERROR = "Une erreur est survenu au niveau du service";
    
    //services
    private IPlan planService;
    private ICategorie categorieService;
    private IUtilisateur utilisateurService;
    
    private Plan openedPlan;
    @FXML
    private Label title;
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
    private ImageView imageCategorie;
    @FXML
    private JFXButton btnValidation;
    @FXML
    private JFXButton btnRefus;
    @FXML
    private Label errorLabel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.planService = new PlanImpl();
        this.categorieService = new CategorieImpl();
        this.utilisateurService = new UtilisateurImpl();
        
        //init template title
        SceneHandler.setTitle(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
        
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
            Categorie categorieFromDatabase = categorieService.getByID(openedPlan.getIdCategorie());
            if(categorieFromDatabase != null) {
                categorie.setText(categorieFromDatabase.getTitre());
            }
            else{
                categorie.setText("");
            }
        }
        
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void validerAction(MouseEvent event) {
        ServiceResponse serviceResponse = new ServiceResponse();
        
        planService.validerPlan(openedPlan,serviceResponse);
        if(serviceResponse.isOk()){
            SceneHandler.openPreviousScene();
        }
        else{
            errorLabel.setText(SERVICE_ERROR);
        }
    }

    @FXML
    private void refuserAction(MouseEvent event) {
        ServiceResponse serviceResponse = new ServiceResponse();
        
        planService.refuserPlan(openedPlan,serviceResponse);
        if(serviceResponse.isOk()){
            SceneHandler.openPreviousScene();
        }
        else{
            errorLabel.setText(SERVICE_ERROR);
        }
    }
    
}
