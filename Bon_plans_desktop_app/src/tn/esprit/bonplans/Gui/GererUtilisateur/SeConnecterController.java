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
import utils.StringUtils;
import utils.entity.EnumGroupe;
import utils.Error;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SeConnecterController extends Application implements Initializable {
    
    private static final String TITLE = "Bon plans";
    private static final String ERR_EMAIL = "Email incorrect.";
    private static final String ERR_PWD = "Mot de passe incorrect.";
    private static final String ERR_EMAIL_PWD = "Email ou mot de passe incorrect.";
    private static final String ERR_NOM = "Nom incorrect.";
    private static final String ERR_PRENOM = "Prénom incorrect.";
    private static final String PATH = "SeConnecter.fxml";
    
    private IUtilisateur userService;
    
    // SeConneter components.
    
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
    
    //S'inscrire components.
    
    @FXML
    private JFXTextField txt_email_signUp;
    @FXML
    private JFXTextField txt_nom_signUp;
    @FXML
    private JFXTextField txt_prenom_signUp;
    @FXML
    private JFXPasswordField txt_pwd_signUp;
    @FXML
    private JFXButton btn_signUp_signUp;
    @FXML
    private Label lbl_error_signUp;
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UtilisateurImpl();
    }
    
    public void btn_cxn_cxn_Click(ActionEvent event) throws NoSuchAlgorithmException {
        String email = txt_email_cxn.getText();
        String pwd = txt_pwd_cxn.getText();
        lbl_error_cxn.setText("");
        if (StringUtils.isEmpty(email)) {
            lbl_error_cxn.setText(ERR_EMAIL);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            lbl_error_cxn.setText(ERR_PWD);
            return;
        }
        Error error = new Error();
        Utilisateur currentUser = userService.connecter(email, pwd, error); 
        if (!error.getMessage().isEmpty()) {
            lbl_error_cxn.setText(error.getMessage());
            return;
        }
        if (currentUser == null) {
            lbl_error_cxn.setText(ERR_EMAIL_PWD);
            return;
        }
        if (currentUser.getIdGroupe() == EnumGroupe.Client.getValue()){
            // Client.
        }
        else {
            // Admin.
        }    
    }

    public void btn_signUp_signUp_Click(ActionEvent event) {
        String email = txt_email_signUp.getText();
        String nom = txt_nom_signUp.getText();
        String prenom = txt_prenom_signUp.getText();
        String pwd = txt_pwd_signUp.getText();
        lbl_error_signUp.setText("");
        if(StringUtils.isEmpty(email) || !StringUtils.isValidEmail(email)) {
            lbl_error_signUp.setText(ERR_EMAIL);
            return;
        }
        if (StringUtils.isEmpty(nom)) {
            lbl_error_signUp.setText(ERR_NOM);
            return;
        }
        if (StringUtils.isEmpty(prenom)) {
            lbl_error_signUp.setText(ERR_PRENOM);
            return;
        }
        if (!StringUtils.isValidPwd(pwd)) {
            lbl_error_signUp.setText(ERR_PWD);
            return;
        }
        Error error = new Error();
        Utilisateur utilisateur = userService.inscrire(email, nom, prenom, pwd, error);
        if (!error.getMessage().isEmpty()) {
            lbl_error_signUp.setText(error.getMessage());
        }
    }
}
