/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation.HistoriqueReserv_Client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Reservation;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IReservation;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.ReservationImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class Historique_ReservClientController  extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Hist_reserv_Client> TabReservClient;
    @FXML
    private JFXTextField Mots;
    @FXML
    private TableColumn<Hist_reserv_Client,Date> DateCol;
    @FXML
    private TableColumn<Hist_reserv_Client, String> Plan;
    @FXML
    private TableColumn<Hist_reserv_Client, String> Catégorie;
    @FXML
    private TableColumn<Hist_reserv_Client, String> Annanceur;
    @FXML
    private TableColumn<Hist_reserv_Client, Hyperlink> colBon;
    @FXML
    private TableColumn<Hist_reserv_Client, Date> DateFin;
    private ObservableList<Hist_reserv_Client>data;
     private ObservableList<Hist_reserv_Client>datachange;
    private IReservation Iresev=new ReservationImpl();
    private IPlan IPlan = new PlanImpl();
    private ICategorie iCategorie= new CategorieImpl();
    private IUtilisateur iUtilisateur= new UtilisateurImpl();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data=FXCollections.observableArrayList();
        List<Reservation> ListReserv= new ArrayList();
        ListReserv=Iresev.selectAll();
        
        for(int i=0; i<ListReserv.size();i++){
           List<Utilisateur> ListAnnonceur= new ArrayList();
           List<Plan> ListPlan= new ArrayList();
           List<Categorie> ListCategorie= new ArrayList();
           ListPlan=IPlan.findOne("idPlan", ListReserv.get(i).getIdPlan());
  
            System.out.println("ninou"+ListPlan);
            System.out.println(ListPlan.get(0).getIdCategorie());
           ListCategorie=iCategorie.findOne("idCategorie",ListPlan.get(0).getIdCategorie());
           ListAnnonceur=iUtilisateur.findOne("idUtilisateur", ListPlan.get(0).getIdAnnonceur());
         Hyperlink hp =new Hyperlink("Bon de resevation");
         hp.setStyle("-fx-text-fill: #f00");
         String link=ListReserv.get(i).getUrlBonRes();
         hp.setOnAction((ActionEvent e) -> {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI(link));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Historique_ReservClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Historique_ReservClientController.class.getName()).log(Level.SEVERE, null, ex);
                }

         });
        Hist_reserv_Client HrC= new Hist_reserv_Client(ListReserv.get(i).getDate(),ListPlan.get(0).getTitre(),ListCategorie.get(0).getTitre(),ListAnnonceur.get(0).getNom(),hp,ListPlan.get(0).getDateFin(),ListReserv.get(i).getIdReservation(),ListReserv.get(i).getIdPlan());
        data.add(HrC);
        
        }
        DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Plan.setCellValueFactory(new PropertyValueFactory<>("Plan"));
        DateFin.setCellValueFactory(new PropertyValueFactory<>("DateFinPromo"));
        Catégorie.setCellValueFactory(new PropertyValueFactory<>("Catégorie"));
        Annanceur.setCellValueFactory(new PropertyValueFactory<>("Annanceur"));
        colBon.setCellValueFactory(new PropertyValueFactory<>("UrlBon"));
        
        TabReservClient.setItems(data);
        
        
       //////////////////////////////////////////
        Mots.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if(newValue.equals("")){
                TabReservClient.setItems(data);
                 
        }
                else{
                   List <Hist_reserv_Client> lt=data.stream().filter(e->e.getPlan().contains(newValue)).collect(Collectors.toList());
                  datachange=FXCollections.observableArrayList(lt);
                
                TabReservClient.setItems(datachange);
               }
                }
        });
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
          Parent root= FXMLLoader.load(getClass().getResource("Historique_ReservClient.fxml"));
           
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            
            
    }
    @FXML
    void OnclickAnnuler(ActionEvent event) {
       Hist_reserv_Client HRC=TabReservClient.getSelectionModel().getSelectedItem();
       Date DateNow= new Date();
       if(DateNow.before(HRC.getDateFinPromo())){
           Reservation Res =new Reservation();
           Res=Iresev.getByID(HRC.getIdReservation());
           Plan Plan = new Plan();
           Plan=IPlan.getByID(HRC.getIdPlan() );
           Plan.setNbPlaceDispo(Plan.getNbPlaceDispo()+Res.getNbplace());
           IPlan.update(Plan);
           Iresev.remove(Res.getIdReservation());
           TabReservClient.getItems().remove(HRC);
       }
    }
    
    
    
     
     
      public static void main(String[] args){launch(args);}
    
}
