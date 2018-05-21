/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.statistique.menu;

import artan.entities.Plan;
import artan.entities.Statistique;
import artan.services.PlanServices;
import artan.services.StatistiqueServices;
import com.gluonhq.charm.glisten.control.DropdownButton;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class StatMenuController extends Application implements Initializable {
    
    public static int idPlan = 0;
    public static int idCat = 0;
    @FXML
    private JFXButton btnStatPlan;
    @FXML
    private JFXButton btnStatCat;

    private ICategorie ic = new CategorieImpl(); 
    private IUtilisateur iu = new UtilisateurImpl(); 
    private IPlan ip = new PlanImpl();
    @FXML
    private ChoiceBox<String> dropPlan;
    @FXML
    private ChoiceBox<String> dropCategorie;
    @FXML
    private ChoiceBox<String> dropMois;
    
    PlanServices ps = new PlanServices();
    StatistiqueServices ss = new StatistiqueServices();
    
    ObservableList<Statistique> toShow = this.getStat();
    ObservableList<Statistique> toShowPire = this.getStatPire();
    
    @FXML
    private TableView<Statistique> tableResultat;
    @FXML
    private JFXButton btnParMois;
    @FXML
    private JFXButton btnparJour;
    @FXML
    private JFXDatePicker dateRecherche;
    @FXML
    private Label labelRes;
    @FXML
    private Label labelRes2;
    @FXML
    private TableView<Statistique> tableResultat1;
    @FXML
    private Label nbLabel;
    @FXML
    private Label moyLabel;
    @FXML
    private JFXButton btnStatP;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Categorie> cats = (ArrayList<Categorie>) ic.selectAll();
        
        ArrayList<String> cs = new ArrayList<>();
        for(Categorie c : cats){
            cs.add(c.getTitre());
        }
        
        dropCategorie.setItems(FXCollections.observableArrayList(cs));
        
        ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) iu.selectAll();
        ArrayList<String> pu = new ArrayList<>();
        for(Utilisateur p : users){
            if(p.getIdGroupe() == 1)
            pu.add(p.getIdUtilisateur() + " - " + p.getNom() + " " + p.getPrenom());
        }
        dropPlan.setItems(FXCollections.observableArrayList(pu));
        
        ArrayList<String> mois = new ArrayList<>();
        mois.add("janvier");
        mois.add("Février");
        mois.add("Mars");
        mois.add("Avril");
        mois.add("Mai");
        mois.add("Juin");
        mois.add("Juillet");
        mois.add("Aout");
        mois.add("Septembre");
        mois.add("Octobre");
        mois.add("Novembre");
        mois.add("Décembre");
        dropMois.setItems(FXCollections.observableArrayList(mois));
        
        TableColumn<Statistique, String> planColonne =  new TableColumn("ID Plan");
        planColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("id"));
        
        TableColumn<Statistique, String> nbColonne =  new TableColumn("Nombre");
        nbColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("nombre"));
        
        TableColumn<Statistique, String> libelleColonne =  new TableColumn("Nom");
        libelleColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("libelle"));
        
        tableResultat.setItems(toShow);
        tableResultat.getColumns().addAll(planColonne, nbColonne, libelleColonne);
        
        TableColumn<Statistique, String> planColonne1 =  new TableColumn("ID Plan");
        planColonne1.setCellValueFactory(new PropertyValueFactory<Statistique, String>("id"));
        
        TableColumn<Statistique, String> nbColonne1 =  new TableColumn("Nombre");
        nbColonne1.setCellValueFactory(new PropertyValueFactory<Statistique, String>("nombre"));
        
        TableColumn<Statistique, String> libelleColonne1 =  new TableColumn("Nom");
        libelleColonne1.setCellValueFactory(new PropertyValueFactory<Statistique, String>("libelle"));
        
        tableResultat1.setItems(toShowPire);
        tableResultat1.getColumns().addAll(planColonne1, nbColonne1, libelleColonne1);
        
        nbLabel.setText("");
        moyLabel.setText("");
    }    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.STAT_MENU);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void statPlanClicked(ActionEvent event) {
        Stage s = new Stage();
        SceneHandler.initPrimaryStage(s);        
        SceneHandler.openScene(SceneEnum.STAT_PLAN);        
    }

    @FXML
    private void statCatClicked(ActionEvent event) {
        
        idCat = ic.findOne("titre", dropCategorie.getValue()).get(0).getIdCategorie();
        
        System.out.println("id cat = " + idCat);
        
        Stage s = new Stage();
        SceneHandler.initPrimaryStage(s);        
        SceneHandler.openScene(SceneEnum.STAT_CAT);
    }

    @FXML
    private void plansParMois(ActionEvent event) {
        
        String choix = dropMois.getValue();
        
        int eq = 0;
        
        switch(choix){
            case "janvier" : eq = 1;
                            break;
            case "Février" : eq = 2;
                            break;
            case "Mars" : eq = 3;
                            break;
            case "Avril" : eq = 4;
                            break;
            case "Mai" : eq = 5;
                            break;
            case "Juin" : eq = 6;
                            break;
            case "Juillet" : eq = 7;
                            break;
            case "Aout" : eq = 8;
                            break;
            case "Septembre" : eq = 9;
                            break;
            case "Octobre" : eq = 10;
                            break;
            case "Novembre" : eq = 11;
                            break;
            case "Décembre" : eq = 12;
                            break;
            default : System.out.println("erreur");
                    break;
        }
        
        toShow.clear();
        
        labelRes.setText("Resultat  (Meilleur dix vente): mois de " + choix);
        
        ArrayList<Statistique> al = ss.meilleurDixVentes();
        System.out.println("liste = " + al);
        
        for(Statistique a : al){
            toShow.add(a);
        }
        
        int nbp = ss.nombreDesPlansParMois(eq);
        double mbp = ss.moyenneDesPlansParJour(eq);
        nbLabel.setText("Nombres des plans pout le mois de " + choix + " : " + nbp);
        moyLabel.setText("Moyenne des plans/jour pout le mois de " + choix + " : " + mbp);
    }

    @FXML
    private void plansParJour(ActionEvent event) {
        
        System.out.println("date = " + dateRecherche.getValue());
        
        toShow.clear();
                
        LocalDate dd =  dateRecherche.getValue();
        
        labelRes.setText("Resultat  (Meilleur dix vente): daté le " + dd.toString());
        
        ArrayList<Statistique> al = ss.meilleursVentesDuJours(dd);
        System.out.println("liste = " + al);
        
        for(Statistique a : al){
            toShow.add(a);
        }
        
        int nbj = ss.nombreDesPlansPourJour(dd);
        nbLabel.setText( "Nombres des plans pour " + dd.toString() + " : " + nbj);
    }

    private ObservableList<Statistique> getStat() {
        System.out.println("liste des meilleurs");
        
        ObservableList<Statistique> resultat = FXCollections.observableArrayList();
        
        for(Statistique p : ss.meilleursVentesDuMois(5)){
            resultat.add(p);
        }
        
        return resultat;
    }

    private ObservableList<Statistique> getStatPire() {
        System.out.println("liste des pire");
        ObservableList<Statistique> resultat = FXCollections.observableArrayList();
        
        for(Statistique p : ss.pireDixVentes()){
            resultat.add(p);
        }
        
        return resultat;
    }

    @FXML
    private void statPClicked(ActionEvent event) {
        System.out.println("clicked");
        
        String chaine = dropPlan.getValue();
        
        String[] items = chaine.split(" ");
        
        int a = Integer.parseInt(items[0]);
        System.out.println("item 0 = " + a);
        
        toShow.clear();
        
        labelRes.setText("Resultat (Meilleur vente): de " + chaine);
        
        ArrayList<Statistique> al = ss.meilleurVenteParPersonne(a);
        System.out.println("liste = " + al);
        
        for(Statistique ax : al){
            toShow.add(ax);
        }
        
        labelRes2.setText("Resultat (pire vente): de " + chaine);
        
        toShowPire.clear();
        ArrayList<Statistique> ap = ss.pireVenteParPersonne(a);
        for(Statistique ax : ap){
            toShowPire.add(ax);
        }
    }
}
