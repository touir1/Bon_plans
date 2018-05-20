/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererPlan.ConsulterPlanUtilisateur;

import artan.plan.liste.PlanListeController;
import artan.services.PlanServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Commentaire;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.ICommentaire;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.CommentaireImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.Converter;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ConsulterPlanController extends Application implements Initializable {

    private IPlan planService;
    private ICategorie categorieService;
    private IUtilisateur utilisateurService;
    
    PlanServices ps = new PlanServices();
    ArrayList<Commentaire> commentaires = ps.listeDesCommentaires(PlanListeController.identifiant);
    
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
    private JFXButton btnReservation;
    @FXML
    private ImageView imagePlan;
    @FXML
    private TextArea commentaireText;
    @FXML
    private JFXButton btnCommenter;
    @FXML
    private Label erreurLabel;
    @FXML
    private TableView<Commentaire> commentairesTable;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        TableColumn<Commentaire, String> commColonne =  new TableColumn("Commentaires");
        commColonne.setMinWidth(200);
        commColonne.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("texte"));
        
        commentairesTable.setItems(this.getCommentaires());
        commentairesTable.getColumns().add(commColonne);
        
        erreurLabel.setText("");
        
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

            if(openedPlan.getNbPlaceDispo() == 0){
                btnReservation.setDisable(true);
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
    private void openReservation(MouseEvent event) {
        SceneHandler.openScene(SceneEnum.RESERVATION);
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        
        erreurLabel.setText("");
        
        ICommentaire commentaire = new CommentaireImpl();
        LocalDate date = java.time.LocalDate.now();
        
        if (commentaireText.getText().isEmpty()) {
            erreurLabel.setText("veuillez saisir un commentaire");
        }else if(this.controle(commentaireText.getText())){
            erreurLabel.setText("yla3enet allah 3lik");
        }else{
            commentaire.save(new Commentaire(commentaireText.getText(), java.sql.Date.valueOf(date), 0, 0, 12, PlanListeController.identifiant));
            System.out.println("commentaire ajouter");         
            
        }
        
    }
 
    public ObservableList<Commentaire> getCommentaires(){
        ObservableList<Commentaire> resultat = FXCollections.observableArrayList();
        
        for(Commentaire c : commentaires){
            resultat.add(c);
        }
        
        return resultat;
    }
    
    public boolean controle(String chaine){
        ArrayList<String> dictionnaire = new ArrayList();
        dictionnaire.add("kelma");
        dictionnaire.add("zeyda");
        
        String[] items = chaine.split(" ");
        
        for (int i = 0; i < items.length; i++) {
            if(dictionnaire.contains(items[i])){
                return true;
            }
        }
        
        return false;
    }
}
