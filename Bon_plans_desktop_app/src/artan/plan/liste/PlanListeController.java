/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.plan.liste;

import artan.entities.Plan;
import artan.services.PlanServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class PlanListeController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public static int identifiant = 0;
    PlanServices ps = new PlanServices();
    ArrayList<Plan> plans = ps.listePlan();
    
    @FXML
    private TableView<Plan> tablePlans;
    @FXML
    private JFXButton btnInspecter;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXButton btnSupprimer;
    
    ObservableList<Plan> toShow = this.getPlans();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TableColumn<Plan, Integer> idColonne =  new TableColumn("ID");
        idColonne.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("idPlan"));
        
        TableColumn<Plan, String> titreColonne =  new TableColumn("Titre");
        titreColonne.setCellValueFactory(new PropertyValueFactory<Plan, String>("titre"));
        
        TableColumn<Plan, String> descriptionColonne =  new TableColumn("Description");
        descriptionColonne.setCellValueFactory(new PropertyValueFactory<Plan, String>("description"));
        
        TableColumn<Plan, String> prixColonne =  new TableColumn("Prix");
        prixColonne.setCellValueFactory(new PropertyValueFactory<Plan, String>("prix"));
        
        TableColumn<Plan, String> qteColonne =  new TableColumn("Quantité disponible");
        qteColonne.setCellValueFactory(new PropertyValueFactory<Plan, String>("quantiteDisponible"));
        
        tablePlans.setItems(toShow);
        tablePlans.getColumns().addAll(idColonne, titreColonne, descriptionColonne, prixColonne, qteColonne);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.LISTE_PLAN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    public ObservableList<Plan> getPlans(){
        ObservableList<Plan> resultat = FXCollections.observableArrayList();
        
        for(Plan p : plans){
            resultat.add(p);
        }
        
        return resultat;
    }
    @FXML
    private void ajouter(ActionEvent event) {
        Stage s = new Stage();
        SceneHandler.initPrimaryStage(s);
        SceneHandler.openScene(SceneEnum.AJOUTER_PLAN);
    }

    @FXML
    private void modifier(ActionEvent event) {
        Stage s = new Stage();
        
        Plan t = tablePlans.getSelectionModel().getSelectedItem();       
        identifiant = t.getIdPlan();
        
        SceneHandler.initPrimaryStage(s);
        SceneHandler.openScene(SceneEnum.MODIFIER_PLAN);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Plan t = tablePlans.getSelectionModel().getSelectedItem();
        
        toShow.remove(t);
        
        identifiant = t.getIdPlan();
        ps.supprimerPlan(identifiant);
    }

    @FXML
    private void inspecter(ActionEvent event) {
        
        Stage s = new Stage();
        
        Plan t = tablePlans.getSelectionModel().getSelectedItem();       
        identifiant = t.getIdPlan();
        
        SceneHandler.initPrimaryStage(s);
        SceneHandler.openScene(SceneEnum.SINGLE_PLAN);
        
    }
}
