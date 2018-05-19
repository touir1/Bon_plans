/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.plan.modifier;

import artan.entities.Plan;
import artan.plan.liste.PlanListeController;
import artan.services.PlanServices;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class PlanModifierController extends Application implements Initializable {
    
    Plan newPlan;
    private ICategorie ic = new CategorieImpl();
    private File f;
    PlanServices ps = new PlanServices();
    Plan plan = ps.rechercheParID(PlanListeController.identifiant);
         
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("id = " + PlanListeController.identifiant);
        
        ArrayList<Categorie> cats = (ArrayList<Categorie>) ic.selectAll();
        ArrayList<String> cs = new ArrayList<>();
        for(Categorie c : cats){
            cs.add(c.getTitre());
        }
        
        categories.setItems(FXCollections.observableArrayList(cs));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        System.out.println(plan);
        
        titre.setText(plan.getTitre());
        prix_initiale.setText(plan.getPrix() + "");
        prix_promo.setText(plan.getPrixPromo() + "");
        qte.setText(plan.getQuantite() + "");
        description.setText(plan.getDescription());
        //System.out.println(date.toString());
        //date_debut.setValue();
        //, , , date_debut, date_fin
    }    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.MODIFIER_PLAN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
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
        }else{
            newPlan = new Plan(plan.getIdPlan(), titre.getText(), description.getText(), "url photo", Double.parseDouble(prix_initiale.getText()), Double.parseDouble(prix_promo.getText()), Integer.parseInt(qte.getText()), java.sql.Date.valueOf(dd),java.sql.Date.valueOf(df), Integer.parseInt(qte.getText()), 0, 33, ic.findOne("titre", categories.getValue()).get(0).getIdCategorie());       
            
            ps.modifierPlan(newPlan);
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
            
            
            newPlan.setUrlPhoto(urlph);
            System.out.println(urlph);
            
        }
    }
    
}
