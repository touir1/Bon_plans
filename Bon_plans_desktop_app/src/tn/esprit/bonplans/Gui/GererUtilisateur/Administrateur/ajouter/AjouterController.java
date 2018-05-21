/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.Administrateur.ajouter;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_EMAIL;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_NOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_PRENOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.INFO;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_SUCC_ADD_ADMIN;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_SUCC_UPDATE;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_STAGE;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.Dialog;
import utils.StringUtils;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class AjouterController extends Application implements Initializable {

    private static final String PATH = "Ajouter.fxml";
    
    private IUtilisateur userService;
    
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_prenom;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new UtilisateurImpl();
    }    

    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE_STAGE);
        primaryStage.show();
    }
    
    @FXML
    public void btn_valider_Click(ActionEvent event){
        String email = txt_email.getText();
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        
        if(StringUtils.isEmpty(email) || !StringUtils.isValidEmail(email)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_EMAIL).showAndWait();
            return;
        }
        if (StringUtils.isEmpty(nom)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_NOM).showAndWait();          
            return;
        }
        if (StringUtils.isEmpty(prenom)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_PRENOM).showAndWait();
            return;
        }
        utils.Error error = new utils.Error();
        Utilisateur newAdmin = userService.ajouterAdministrateur(email, nom, prenom, error);
        if (!error.getMessage().isEmpty()) {
            Dialog.Error(ERR, ERR_INPUT, error.getMessage()).showAndWait();
            return;
        }
        Dialog.Info(INFO, null, MSG_SUCC_ADD_ADMIN).showAndWait();
    }
    
    @FXML
    public void btn_annuler_Click(ActionEvent event){
        txt_email.setText("");
        txt_nom.setText("");
        txt_prenom.setText("");
    }
}
