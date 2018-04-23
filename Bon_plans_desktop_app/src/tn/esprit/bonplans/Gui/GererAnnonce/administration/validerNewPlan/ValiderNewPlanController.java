/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererAnnonce.administration.validerNewPlan;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.service.ServiceResponse;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ValiderNewPlanController extends Application implements Initializable {

    private final String FXML_FILE_PATH = "ValiderNewPlan.fxml";
    private final String ICON_PATH = "./resources/images/Logo.png";
    private final String TITLE = "Validation nouveau plans";
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
        //init template title
        SceneHandler.setTitle(SceneEnum.VALIDER_NEW_PLAN);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.VALIDER_NEW_PLAN);
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
            initListPlan();

        }
    }
    
    private void initListPlan(){
        ServiceResponse serviceResponse = new ServiceResponse();
        List<Plan> plans = planService.getListOfNonValidatedPlans(serviceResponse);
        if(serviceResponse.isOk()){
            ObservableList observableList = FXCollections.observableArrayList(plans);
            listePlanNonValide.setItems(observableList);
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            urlPhoto.setCellValueFactory(new PropertyValueFactory<>("urlPhoto"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            annonceur.setCellValueFactory(c-> {
                String result = null;
                serviceResponse.initResponse();
                if(c.getValue().getIdAnnonceur() != 0){
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
    
    //USED ONLY FOR DOUBLE CLICK
    private Plan temp;
    private Date lastClickTime;
    
    @FXML
    private void doubleClickPlan() {
        initDoubleClickOut();
        Plan row = listePlanNonValide.getSelectionModel().getSelectedItem();
        if (row == null){
            listePlanNonValide.getSelectionModel().clearSelection();
            return;
        }
        if(!row.equals(temp)){
            temp = row;
            lastClickTime = new Date();
        } 
        else {
            Date now = new Date();
            long diff = now.getTime() - lastClickTime.getTime();
            if (diff < 300){ //another click registered in 300 millis
                 //TODO
                CurrentSession.addData("plan", row);
                System.out.println("Implement open plan please xD");
            } else {
                lastClickTime = new Date();
            }
        }
    }
    
    //USED ONLY FOR INIT DOUBLE CLICK OUT
    private boolean isInitDoubleClickOut = false;
    private void initDoubleClickOut(){
        if(!isInitDoubleClickOut){
            Scene scene = listePlanNonValide.getScene();
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
                Node source = evt.getPickResult().getIntersectedNode();

                // move up through the node hierarchy until a TableRow or scene root is found 
                while (source != null && !(source instanceof TableRow)) {
                    source = source.getParent();
                }


                // clear selection on click anywhere but on a filled row
                if (source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
                    listePlanNonValide.getSelectionModel().clearSelection();
                }
            });
            isInitDoubleClickOut = true;
        }
    }
}
