/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SeConnecterController extends Application implements Initializable {
    
    private static final String TITLE = "Bon plans";
    private static final String ERR1 = "Email incorrect.";
    private static final String ERR2 = "Mot de passe incorrect.";
    private static final String ERR12 = "Email ou mot de passe incorrect.";
    
    private IUtilisateur userService;
    
    @FXML
    private JFXTextField txt_email_cxn;
    @FXML
    private JFXPasswordField txt_pwd_cxn;
    @FXML
    private Label lbl_error_cxn;
    @FXML
    private JFXButton btn_cxn_cxn;
    @FXML
    private JFXButton btn_cnxGgle_cxn;
    @FXML
    private Hyperlink hln_pwd4get_cnx;
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SeConnecter.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();
    }
    
    public void btn_cxn_cxn_Click(ActionEvent event) throws NoSuchAlgorithmException {
        String email = txt_email_cxn.getText();
        String pwd = txt_pwd_cxn.getText();
        lbl_error_cxn.setText("");
        if (email.isEmpty()) {
            lbl_error_cxn.setText(ERR1);
            return;
        }
        if (pwd.isEmpty()) {
            lbl_error_cxn.setText(ERR2);
            return;
        }
        Utilisateur currentUser = userService.seConnecter(email, pwd); 
        if (currentUser == null) {
            lbl_error_cxn.setText(ERR12);
            return;
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UtilisateurImpl();
    }

}
