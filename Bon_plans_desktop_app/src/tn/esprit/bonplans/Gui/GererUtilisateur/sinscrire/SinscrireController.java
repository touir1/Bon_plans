/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.sinscrire;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import utils.StringUtils;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SinscrireController implements Initializable {

    private static final String ERR_PRENOM = "Prénom incorrect.";
    private static final String TXT_CONFIRMATION = "Nous vous avons envoyé un courriel de validation.\nPour poursuivre le processus de création du compte, veuillez introduire le code d'activation.";
    private static final String ERR_NOM = "Nom incorrect.";
    private static final String ERR_EMAIL = "Email incorrect.";
    private static final String ERR_PWD = "Mot de passe incorrect.";
    
    private IUtilisateur userService;

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
    private JFXTextArea lbl_error_signUp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        utils.Error error = new utils.Error();
        Utilisateur utilisateur = userService.inscrire(email, nom, prenom, pwd, error);
        if (!error.getMessage().isEmpty()) {
            lbl_error_signUp.setText(error.getMessage());
            return;
        }
        lbl_error_signUp.setText(TXT_CONFIRMATION);
        
    }
}
