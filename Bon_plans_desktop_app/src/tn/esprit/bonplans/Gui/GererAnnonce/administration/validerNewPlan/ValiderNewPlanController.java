/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validerNewPlan;

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

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderNewPlanController extends Application implements Initializable {

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
        Parent root = FXMLLoader.load(getClass().getResource("ValiderNewPlan.fxml"));
        
        Scene Scene = new Scene(root);
        primaryStage.getIcons().add(new Image("./resources/images/Logo.png"));
        primaryStage.setScene(Scene);
        primaryStage.show();
        primaryStage.setTitle("Validation nouveau plans");
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @FXML
    public void buttonValiderClick(){
        Plan plan = listePlanNonValide.getSelectionModel().getSelectedItem();
        System.out.println(plan);
        if(plan == null){
            errorLabel.setText("Veuillez sélectionner un plan à valider");
        }
        else{
            errorLabel.setText("");
            planService.validerPlan(plan);
            initListPlan();

        }
    }
    
    
    private void initListPlan(){
        List<Plan> plans = planService.getListOfNonValidatedPlans();
        ObservableList observableList = FXCollections.observableArrayList(plans);
        listePlanNonValide.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        urlPhoto.setCellValueFactory(new PropertyValueFactory<>("urlPhoto"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        annonceur.setCellValueFactory(c-> {
            String result = null;
            if(c.getValue().getIdAnnonceur() != 0){
                Utilisateur utilisateur = utilisateurService.getByID(c.getValue().getIdAnnonceur());
                if(utilisateur != null){
                    result = utilisateur.getNom() + " "+utilisateur.getPrenom();
                }
            }
            return new SimpleStringProperty(result);      
        });
        
        categorie.setCellValueFactory(c-> {
            String result = null;
            if(c.getValue().getIdCategorie() != 0){
                Categorie categorie = categorieService.getByID(c.getValue().getIdCategorie());
                if(categorie != null){
                    result = categorie.getTitre();
                }
            }
            return new SimpleStringProperty(result);      
        });
    }

    @FXML
    private void buttonRefuserClick(ActionEvent event) {
        Plan plan = listePlanNonValide.getSelectionModel().getSelectedItem();
        System.out.println(plan);
        if(plan == null){
            errorLabel.setText("Veuillez sélectionner un plan à valider");
        }
        else{
            errorLabel.setText("");
            planService.refuserPlan(plan);
            initListPlan();

        }
    }
}
