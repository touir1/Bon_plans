/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validerModifiedPlan;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.service.ServiceResponse;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderModifiedPlanController extends Application implements Initializable {

    private final String FXML_FILE_PATH = "ValiderModifiedPlan.fxml";
    private final String ICON_PATH = "./resources/images/Logo.png";
    private final String TITLE = "Validation plans modifiés";
    private final String ERROR_NO_PLAN_SELECTED = "Veuillez sélectionner un plan à valider";
    private final String ERROR_SERVICE = "Une erreur est survenu au niveau du service";
    private final String EMPTY = "";
    
    private IPlan planService;
    private IUtilisateur utilisateurService;
    private ICategorie categorieService;
    
    //FXML elements
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Plan> listePlanNonValide;
    @FXML
    private TableColumn<Plan, String> titre;
    @FXML
    private TableColumn<Plan, String> description;
    @FXML
    private TableColumn<Plan, String> urlPhoto;
    @FXML
    private TableColumn<Plan, String> annonceur;
    @FXML
    private TableColumn<Plan, String> categorie;
    @FXML
    private JFXButton buttonValider;
    @FXML
    private JFXButton buttonRefuser;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //init services
        planService = new PlanImpl();
        categorieService = new CategorieImpl();
        utilisateurService = new UtilisateurImpl();
        
        //init data
        initListPlan();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE_PATH));
        
        Scene Scene = new Scene(root);
        primaryStage.getIcons().add(new Image(ICON_PATH));
        primaryStage.setScene(Scene);
        primaryStage.show();
        primaryStage.setTitle(TITLE);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @FXML
    public void buttonValiderClick(){
        Plan plan = listePlanNonValide.getSelectionModel().getSelectedItem();
        
        System.out.println(plan);
        if(plan == null){
            errorLabel.setText(ERROR_NO_PLAN_SELECTED);
        }
        else{
            errorLabel.setText(EMPTY);
            
            ServiceResponse serviceResponse = new ServiceResponse();
            planService.validerPlan(plan,serviceResponse);
            if(!serviceResponse.isOk()){
                errorLabel.setText(ERROR_SERVICE);
            }
            else{
                initListPlan();
            }

        }
    }
    
    
    private void initListPlan(){
        ServiceResponse serviceResponse = new ServiceResponse();
        List<Plan> plans = planService.getListOfModifiedPlans(serviceResponse);
        if(serviceResponse.isOk()){
            ObservableList observableList = FXCollections.observableArrayList(plans);
            listePlanNonValide.setItems(observableList);
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            urlPhoto.setCellValueFactory(new PropertyValueFactory<>("urlPhoto"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            annonceur.setCellValueFactory(c-> {
                String result = null;
                if(c.getValue().getIdAnnonceur() != 0){
                    serviceResponse.initResponse();
                    Utilisateur utilisateur = utilisateurService.getByID(c.getValue().getIdAnnonceur(),serviceResponse);
                    if(serviceResponse.isOk()){
                        if(utilisateur != null){
                            result = utilisateur.getNom() + " "+utilisateur.getPrenom();
                        }
                    }
                    else{
                        errorLabel.setText(ERROR_SERVICE);
                    }
                }
                return new SimpleStringProperty(result);      
            });

            categorie.setCellValueFactory(c-> {
                String result = null;
                serviceResponse.initResponse();
                if(c.getValue().getIdCategorie() != 0){
                    Categorie categorie = categorieService.getByID(c.getValue().getIdCategorie(),serviceResponse);
                    if(serviceResponse.isOk()){
                        if(categorie != null){
                            result = categorie.getTitre();
                        }
                    }
                    else{
                        errorLabel.setText(ERROR_SERVICE);
                    }
                }
                return new SimpleStringProperty(result);      
            });
        }
        else{
            errorLabel.setText(ERROR_SERVICE);
        }
    }

    @FXML
    private void buttonRefuserClick(ActionEvent event) {
        Plan plan = listePlanNonValide.getSelectionModel().getSelectedItem();
        System.out.println(plan);
        if(plan == null){
            errorLabel.setText(ERROR_NO_PLAN_SELECTED);
        }
        else{
            errorLabel.setText(EMPTY);
            ServiceResponse serviceResponse = new ServiceResponse();
            planService.refuserPlan(plan,serviceResponse);
            if(!serviceResponse.isOk()){
                errorLabel.setText(ERROR_SERVICE);
            }
            else{
                initListPlan();
            }
            initListPlan();

        }
    }
}