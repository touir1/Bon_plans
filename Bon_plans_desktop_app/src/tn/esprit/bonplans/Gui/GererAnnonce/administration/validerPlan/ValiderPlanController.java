/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validerPlan;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.implementation.PlanImpl;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderPlanController extends Application implements Initializable {

    private IPlan planService;
    
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //init services
        planService = new PlanImpl();
        
        //init data
        List<Plan> plans = planService.getListOfNonValidatedPlans();
        ObservableList observableList = FXCollections.observableArrayList(plans);
        listePlanNonValide.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        urlPhoto.setCellValueFactory(new PropertyValueFactory<>("urlPhoto"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
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
    }
}
