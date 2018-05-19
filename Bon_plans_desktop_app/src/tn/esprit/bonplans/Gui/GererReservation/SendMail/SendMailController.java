/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation.SendMail;

import com.sun.glass.ui.Application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static tn.esprit.bonplans.Gui.GererReservation.ListeReservation_Annonceur.ListeReservation_AnnonceurController.ClientMail;
import utils.Email;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class SendMailController extends javafx.application.Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea Message;
     @FXML
    private TextField Sujet;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @Override
    public void start(Stage primaryStage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("SendMail.fxml"));
        
      Scene Scene = new Scene(root);
     primaryStage.setTitle("bons Plans");
     primaryStage.getIcons().add(new Image("http://localhost/bon_plans_api/uploads/Logo.png"));
     primaryStage.setScene(Scene);
     primaryStage.show();
//         SceneHandler.initPrimaryStage(primaryStage);
//        SceneHandler.openScene(SceneEnum.HistoriqueRESERVATIONAnnonceur);
    }
    
    @FXML
    public void onClickEnvoyer(ActionEvent event){
    if((Message.getText().isEmpty())||(Sujet.getText().isEmpty())){
    System.out.println("veuillez remplir tous les champs !");
    }
    else{
       Boolean send=false; 
    send=Email.send(ClientMail.getEmail(), Sujet.getText(), Message.getText());
    if(send==true){
        System.out.println("Email envoyé !");
    }
    else
        System.out.println("Email nom envoyé,merci de verifier votre connection internet !");
    }
    
    }
      public static void main(String[] args){javafx.application.Application.launch(args);}
    
}
