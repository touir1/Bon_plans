/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation.ListeReservation_Annonceur;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.esprit.bonplans.Gui.GererReservation.HistoriqueReserv_Client.Historique_ReservClientController;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Reservation;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IReservation;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.ReservationImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class ListeReservation_AnnonceurController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Plan> cmbListPlan;
    @FXML
    private TableView<ResevAnn> tabReservation;
    @FXML
    private TableColumn<ResevAnn,Date> Date;
    @FXML
    private TableColumn<ResevAnn, String> Quantite;
    @FXML
    private TableColumn<ResevAnn, Integer> Client;
     @FXML
    private TableColumn<ResevAnn, Hyperlink> BonRes;
    private ObservableList<Plan>dataCombo;
    
    private ObservableList<ResevAnn>data;
     private IReservation Ir=new ReservationImpl();
     private IPlan IP=new PlanImpl();
     private IUtilisateur Iu=new UtilisateurImpl();
     public static Utilisateur ClientMail;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         SceneHandler.setTitle(SceneEnum.HistoriqueRESERVATIONAnnonceur);
            ///
            
            //session id 
          dataCombo=FXCollections.observableArrayList(IP.findOne("idAnnonceur",CurrentSession.getUtilisateur().getIdUtilisateur()));
          data=FXCollections.observableArrayList();
          cmbListPlan.setItems(dataCombo);
          cmbListPlan.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
          if(newValue.equals("Liste des plans")){
              data.clear();
          }
           else{
           //// tableau ///////
            List <Reservation>ListReservation= new ArrayList<>();
            ListReservation=Ir.findOne("idPlan", cmbListPlan.getSelectionModel().getSelectedItem().getIdPlan());
            if(!ListReservation.isEmpty()){
                for(int i=0;i<ListReservation.size();i++){
                    if(ListReservation.get(i).getStatus()==0){
                    Utilisateur Client= Iu.getByID(ListReservation.get(i).getIdClient());
                     Hyperlink hp =new Hyperlink("Bon de resevation");
                                hp.setStyle("-fx-text-fill: #f00");
                                String link=ListReservation.get(i).getUrlBonRes();
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
                
                    ResevAnn RA= new ResevAnn(ListReservation.get(i).getIdReservation(),ListReservation.get(i).getDate(),ListReservation.get(i).getNbplace(),Client, hp);
                     data.add(RA);
                }}
                 Date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
                 Client.setCellValueFactory(new PropertyValueFactory<>("Client"));
                 BonRes.setCellValueFactory(new PropertyValueFactory<>("urlBon"));
                 tabReservation.setItems(data);
            
          }
          }}
    );
    }    
    
    @FXML
    void onclickValider(ActionEvent event) throws Exception {
      ResevAnn R=tabReservation.getSelectionModel().getSelectedItem();
      if(R!=null){
      Reservation Reserv =Ir.getByID(R.getIdReservation());
      Reserv.setStatus(1);
      Ir.update(Reserv);
      tabReservation.getItems().remove(R);}
      
           
    }
    @FXML
    void OnclickEnvoyerEmail(ActionEvent event) throws Exception {
       ClientMail=tabReservation.getSelectionModel().getSelectedItem().getClient();
       if(ClientMail!=null){
       Parent root= FXMLLoader.load(getClass().getResource("../SendMail/SendMail.fxml"));
          Scene Scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Scene);
          window.show();}
           
    }
     @FXML
    void onclickPlan(ActionEvent event) throws Exception {
      
           
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("ListeReservation_Annonceur.fxml"));
        
        Scene Scene = new Scene(root);
     primaryStage.setTitle("bons Plans");
     primaryStage.getIcons().add(new Image("http://localhost/bon_plans_api/uploads/Logo.png"));
     primaryStage.setScene(Scene);
     primaryStage.show();
//         SceneHandler.initPrimaryStage(primaryStage);
//        SceneHandler.openScene(SceneEnum.HistoriqueRESERVATIONAnnonceur);
    }
    
      public static void main(String[] args){Application.launch(args);}
    
}
