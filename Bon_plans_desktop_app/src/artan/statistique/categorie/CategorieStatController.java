/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.statistique.categorie;

import artan.entities.Statistique;
import artan.services.StatistiqueServices;
import artan.statistique.menu.StatMenuController;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class CategorieStatController extends Application implements Initializable {
    
    private ICategorie ic = new CategorieImpl();
    private Categorie categorie = ic.getByID(StatMenuController.idCat);
    
    private StatistiqueServices ss = new StatistiqueServices();
    
    @FXML
    private TableView<Statistique> tableResultat;
    @FXML
    private Label titreLable;
    @FXML
    private JFXButton btnMV;
    @FXML
    private JFXButton btnMDV;
    @FXML
    private JFXButton btnPV;
    @FXML
    private JFXButton btnPDV;
    
    ObservableList<Statistique> toShow = this.getStat();
    
    @FXML
    private Label labelRes;
    
    @FXML
    private ChoiceBox<String> dropCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titreLable.setText("Statistique de categorie : " + categorie.getTitre());
        labelRes.setText("Resultat (Meilleur ventes):");
        
        TableColumn<Statistique, String> planColonne =  new TableColumn("ID Plan");
        planColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("id"));
        
        TableColumn<Statistique, String> nbColonne =  new TableColumn("Nombre");
        nbColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("nombre"));
        
        TableColumn<Statistique, String> libelleColonne =  new TableColumn("Nom");
        libelleColonne.setCellValueFactory(new PropertyValueFactory<Statistique, String>("libelle"));
        
        tableResultat.setItems(toShow);
        tableResultat.getColumns().addAll(planColonne, nbColonne, libelleColonne);
        
        ArrayList<Categorie> cats = (ArrayList<Categorie>) ic.selectAll();
        
        ArrayList<String> cs = new ArrayList<>();
        for(Categorie c : cats){
            cs.add(c.getIdCategorie() +  " - " +c.getTitre());
        }
        dropCat.setItems(FXCollections.observableArrayList(cs));
    } 
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.STAT_CAT);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void btnMVClicked(ActionEvent event) {
        toShow.clear();        
        labelRes.setText("Resultat (Meilleur ventes): ");
        
        ArrayList<Statistique> al = ss.meilleurVenteParCategorie(categorie.getIdCategorie());
        System.out.println("liste = " + al);
        
        for(Statistique a : al){
            toShow.add(a);
        }
    }

    @FXML
    private void btnMDVClicked(ActionEvent event) {
        System.out.println("btnMDVClicked clicked");
        
        String chaine = dropCat.getValue();
        String[] items = chaine.split(" ");
        
        int a = Integer.parseInt(items[0]);
        System.out.println("item 0 = " + a);
        
        toShow.clear();
        
        labelRes.setText("Resultat (Meilleur dix vente): de " + chaine);
        
        ArrayList<Statistique> al = ss.meilleurDixVentesParCategorie().get(a);
        System.out.println("liste = " + al);
        
        for(Statistique ax : al){
            toShow.add(ax);
        }
    }

    @FXML
    private void btnPVClicked(ActionEvent event) {
        toShow.clear();        
        labelRes.setText("Resultat (Pire ventes): ");
        
        ArrayList<Statistique> al = ss.pireVenteParCategorie(categorie.getIdCategorie());
        System.out.println("liste = " + al);
        
        for(Statistique a : al){
            toShow.add(a);
        }
    }

    @FXML
    private void btnPDVClicked(ActionEvent event) {
        System.out.println("btnPDVClicked clicked");
        
        String chaine = dropCat.getValue();
        String[] items = chaine.split(" ");
        
        int a = Integer.parseInt(items[0]);
        System.out.println("item 0 = " + a);
        
        toShow.clear();
        
        labelRes.setText("Resultat (pire dix vente): de " + chaine);
        
        ArrayList<Statistique> al = ss.pireDixVentesParCategorie().get(a);
        System.out.println("liste = " + al);
        
        for(Statistique ax : al){
            toShow.add(ax);
        }
    }

    private ObservableList<Statistique> getStat() {
        System.out.println("liste des meilleurs ventes par categories");
        
        ObservableList<Statistique> resultat = FXCollections.observableArrayList();
        
        for(Statistique p : ss.meilleurVenteParCategorie(categorie.getIdCategorie())){
            resultat.add(p);
        }
        
        return resultat;
    }
    
}
