/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.plan.create;

import artan.entities.Plan;
import artan.services.PlanServices;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class PlanCreateController extends Application  implements Initializable {

    Plan plan;
    
    String tempURL;

    private ICategorie ic = new CategorieImpl();
    private File f;
         
    List<String> lstfile;
    
    @FXML
    private TextField titre, prix_initiale, prix_promo, qte;
    
    @FXML
    private TextArea description;
    
    @FXML
    private DatePicker date_debut, date_fin;
    
    @FXML
    private Label erreur;
    
    @FXML
    private ChoiceBox<String> categories;
    @FXML
    private Button image;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstfile = new ArrayList<>();
        lstfile.add("*.png");
        lstfile.add("*.jpeg");
        lstfile.add("*.jpg");
        
        ArrayList<Categorie> cats = (ArrayList<Categorie>) ic.selectAll();
        
        ArrayList<String> cs = new ArrayList<>();
        for(Categorie c : cats){
            cs.add(c.getTitre());
        }
        
        categories.setItems(FXCollections.observableArrayList(cs));
    }
    
    
    
    @FXML
    private void onClickButton(ActionEvent event) {
        LocalDate dd = date_debut.getValue();
        LocalDate df = date_fin.getValue();

        if (titre.getText().isEmpty()) {
            erreur.setText("Le champ titre est vide");
        }else if (description.getText().isEmpty()) {
            erreur.setText("Le champ description est vide");
        }else if (prix_initiale.getText().isEmpty()) {
            erreur.setText("Le champ prix initial est vide");
        }else if (prix_promo.getText().isEmpty()) {
            erreur.setText("Le champ prix promotion est vide");
        }else if (qte.getText().isEmpty()){
            erreur.setText("Le champ quatite est vide");
        }else if (dd == null){
            erreur.setText("Le champ date de début est vide");
        }else if (df == null) {
            erreur.setText("Le champ date fin est vide");
        }else if (dd.isAfter(df)) {
            erreur.setText("Les dates sont incompatibles");
        }else if(f == null){
            erreur.setText("veillez choisir une photo pour votre plan");
        }else{
            
            plan = new Plan(titre.getText(), description.getText(), tempURL, Double.parseDouble(prix_initiale.getText()), Double.parseDouble(prix_promo.getText()), Integer.parseInt(qte.getText()), java.sql.Date.valueOf(dd),java.sql.Date.valueOf(df), Integer.parseInt(qte.getText()), 0, 33, ic.findOne("titre", categories.getValue()).get(0).getIdCategorie());       
            
            /*
            plan.setTitre(titre.getText());
            plan.setDescription(description.getText());
            plan.setPrix(Double.parseDouble(prix_initiale.getText()));
            plan.setPrixPromo(Double.parseDouble(prix_promo.getText()));
            plan.setQuantite(Integer.parseInt(qte.getText()));
            plan.setDateDebut(java.sql.Date.valueOf(dd));
            plan.setDateFin(java.sql.Date.valueOf(df));
            plan.setIdAnnonceur(1);
            plan.setIdCategorie(ic.findOne("titre", categories.getValue()).get(0).getIdCategorie());
            */
            
            PlanServices ps = new PlanServices();
            
            ps.ajouterPlan(plan);
        }
    }

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", lstfile));
        f = fc.showOpenDialog(null);
        if (f != null) {
            boolean backgroundLoading = true;
            
            System.out.println(f.getPath());
            String urlph = "file:///" + f.getAbsolutePath();
            Image i = new Image(urlph, backgroundLoading);
            
            
            tempURL = urlph;
            System.out.println(tempURL);
            
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.AJOUTER_PLAN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
