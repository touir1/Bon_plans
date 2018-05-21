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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Commentaire;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IAvis;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.ICommentaire;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.AvisImpl;
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
 *
 */
public class ConsulterPlanController extends Application implements Initializable {
    
    private Utilisateur user;
    
    private IPlan planService;
    private ICategorie categorieService;
    private IUtilisateur utilisateurService;
    
    private PlanServices ps;
    private ArrayList<Commentaire> commentaires;
    
    private artan.entities.Plan openedPlan;
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
    @FXML
    private Button btmodifier;
    @FXML
    private Button btsupprimer;
    
    private ObservableList<Commentaire> toShow;
    
    public static int identifiant =0;
    
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    @FXML
    private Button aimer;
    @FXML
    private Button aimerpas;
    @FXML
    private Label lbjaime;
    @FXML
    private Label lbjaimepas;
    
    private int nvlike;
    private int nvdislike;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        user = CurrentSession.getUtilisateur();
        ps = new PlanServices();
        
        Plan openedPlanFromSession = (Plan) CurrentSession.getData("openedPlan");
        
        commentaires = ps.listeDesCommentaires(openedPlanFromSession.getIdPlan());
        openedPlan = ps.rechercheParID(openedPlanFromSession.getIdPlan());
        toShow = this.getCommentaires();
        
        nvlike = openedPlan.getLike();
        nvdislike = openedPlan.getDislike();
        
        rating.ratingProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number t, Number t1) {
                //throw new UnsupportedOperationException("Not supported yet."); 
                msg.setText("Rating : " + t1.toString());
            }

        });
  
       
       
        TableColumn<Commentaire, String> commColonne =  new TableColumn("Commentaires");
        commColonne.setMinWidth(200);
        commColonne.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("texte"));
        
        commentairesTable.setItems(toShow);
        commentairesTable.getColumns().add(commColonne);
        
        erreurLabel.setText("");
        
        this.planService = new PlanImpl();
        this.categorieService = new CategorieImpl();
        this.utilisateurService = new UtilisateurImpl();
        
        //init template title
        SceneHandler.setTitle(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
        
        //get plan opened
        if(openedPlan != null){
            title.setText(openedPlan.getTitre());
            description.setText(openedPlan.getDescription());
            initialPrice.setText(openedPlan.getPrix()+" TND");
            promoPrice.setText(openedPlan.getPrixPromo()+" TND");
            promoPercentage.setText(Math.round(100*openedPlan.getPrixPromo()/openedPlan.getPrix())+"%");
            units.setText(Integer.toString(openedPlan.getQuantite()));
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

            if(openedPlan.getQuantiteDisponible()== 0){
                btnReservation.setDisable(true);
            }
            
            lbjaime.setText(Integer.toString(openedPlan.getLike()));
            nvlike = openedPlan.getLike();
            lbjaimepas.setText(Integer.toString(openedPlan.getDislike()));
            nvdislike = openedPlan.getDislike();
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
            erreurLabel.setText("vous avez saisi des gros mots");
        }else{
            commentaire.save(new Commentaire(commentaireText.getText(), java.sql.Date.valueOf(date), 0, 0, user.getIdUtilisateur(), openedPlan.getIdPlan()));
            System.out.println("commentaire ajouté");         
            
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
        dictionnaire.add("putain");
        dictionnaire.add("merde");
        dictionnaire.add("fuck");
        dictionnaire.add("pétasse");
         
        
        
        String[] items = chaine.split(" ");
        
        for (int i = 0; i < items.length; i++) {
            if(dictionnaire.contains(items[i])){
                return true;
            }
        }
        
        return false;
    }

    @FXML
    private void modifiercommentaire(ActionEvent event) {        
          ICommentaire commentaire = new CommentaireImpl();
          LocalDate date = java.time.LocalDate.now();
           if (commentaireText.getText().isEmpty()) {
            erreurLabel.setText("veuillez saisir un commentaire");
            }else if(this.controle(commentaireText.getText())){
                erreurLabel.setText("vous avez saisi des gros mots");
            }else{
                Commentaire c = commentairesTable.getSelectionModel().getSelectedItem();
                c.setTexte(commentaireText.getText());
                commentaire.update(c);
                System.out.println("commentaire modifié ");
            }                
    }

    @FXML
    private void supprimercommentaire(ActionEvent event) {
        System.out.println("supprimer clicked");
        ICommentaire commentaire = new CommentaireImpl();
        Commentaire c = commentairesTable.getSelectionModel().getSelectedItem();
        toShow.remove(c);
        commentaire.remove(c.getIdCommentaire());
        commentaireText.setText("");
        System.out.println("commentaire supprimer");
    }

    @FXML
    private void lsiteClicked(MouseEvent event) {
        Commentaire c = commentairesTable.getSelectionModel().getSelectedItem();
        identifiant = c.getIdCommentaire();
        
        commentaireText.setText(c.getTexte());
    }

    @FXML
    private void aimercommentaire(ActionEvent event) {
        System.out.println("id = " + openedPlan.getIdPlan() + " nb = " + openedPlan.getLike());
        ps.incLike(openedPlan.getIdPlan(), nvlike);
        nvlike = nvlike + 1;
        //label like
        lbjaime.setText(Integer.toString(nvlike));
    }

    @FXML
    private void aimerpas(ActionEvent event) {
        System.out.println("id = " + openedPlan.getIdPlan() + " nb = " + openedPlan.getDislike());
        ps.incDislike(openedPlan.getIdPlan(), nvdislike);
        
        nvdislike = nvdislike + 1;
        //label dislike
        lbjaimepas.setText(Integer.toString(nvdislike));
    }

 
    

 
}
