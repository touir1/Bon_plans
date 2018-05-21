/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.Administrateur.consulter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
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
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class ConsulterCompteController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private IUtilisateur ic =new UtilisateurImpl();
    private IPlan ip= new PlanImpl();
    @FXML
    private Label LblError;
    @FXML
    private TableView<Utilisateur> Utilisateurs ;
    private ObservableList<Utilisateur>data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data=FXCollections.observableArrayList();
        List<Utilisateur> ls=ic.selectAll();
        for (Utilisateur l : ls) {
            if (l.getAdresse() == null) {
                l.setAdresse(" ");
            }
        }
       
        data.addAll(ls);
        
        //TableColum clmGrp = new TableColumn<>("Groupe");
        TableColumn tcC1 = new TableColumn<>("Nom");
        tcC1.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        TableColumn tc3 = new TableColumn<>("Prenom");
        tc3.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TableColumn tc2 = new TableColumn<>("Address");
        tc2.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        TableColumn tc4 = new TableColumn<>("Email");
        tc4.setCellValueFactory(new PropertyValueFactory<>("email"));
        Utilisateurs.getColumns().add(tcC1);
        Utilisateurs.getColumns().add(tc3);
        Utilisateurs.getColumns().add(tc2);
        Utilisateurs.getColumns().add(tc4);
         
        
        Utilisateurs.setItems(data);
        
        
    }    
    
    
  
    
      @FXML
    void OuvrirePageAjouterCategorie(ActionEvent event) throws IOException {
       SceneHandler.openScene(SceneEnum.AJOUTER_CATEGORIE);
    }
    @FXML
   void  onClickSupprimer(ActionEvent event) throws IOException {
    Utilisateur ct=Utilisateurs.getSelectionModel().getSelectedItem();
         if(ct.getIdGroupe()==2){
        
        ic.remove(ct.getIdUtilisateur());
        Utilisateurs.getItems().remove(ct);}
         else{
            LblError.setText("Vous n'avez pas le droit de supprimer un compte client");
         }
   }

    @Override
    public void start(Stage primaryStage) throws Exception {
           Parent root= FXMLLoader.load(getClass().getResource("ConsulterCompte.fxml"));
           
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            
            
    }
    
    public static void main(String[] args){launch(args);}
}
