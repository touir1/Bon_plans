/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.sinscrire;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
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
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_CONST_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_CODE_ACTIVATION;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_EMAIL;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_NOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_PRENOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_CONFIRMATION;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_ACTIVATION;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_STAGE;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.Dialog;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.StringUtils;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SinscrireController extends Application implements Initializable {

    private IUtilisateur userService;
    public static final String PATH = "Sinscrire.fxml";
    
    //S'inscrire components.
    
    @FXML
    private JFXTextField txt_email_signUp;
    @FXML
    private JFXTextField txt_nom_signUp;
    @FXML
    private JFXTextField txt_prenom_signUp;
    @FXML
    private JFXPasswordField txt_pwd_signUp;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new UtilisateurImpl();
    }    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE_STAGE);
        primaryStage.show();
    }
    
    // Button : se connecter.
    @FXML
    void btn_login_signUp_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.SE_CONNECTER);
    }
    
    // Button : s'inscrire.
    @FXML
    public void btn_signUp_signUp_Click(ActionEvent event) {
        
        // Récupération des champs.
        String email = txt_email_signUp.getText();
        String nom = txt_nom_signUp.getText();
        String prenom = txt_prenom_signUp.getText();
        String pwd = txt_pwd_signUp.getText();
       
        // Controle de saisie.
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
        if (!StringUtils.isValidPwd(pwd)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_CONST_PWD).showAndWait();
            return;
        }
        
        utils.Error error = new utils.Error();
        Utilisateur currentUser = userService.inscrire(email, nom, prenom, pwd, error);
        if (!error.getMessage().isEmpty()) {
            Dialog.Error(ERR, ERR_INPUT, error.getMessage()).showAndWait();
            return;
        }
        
        if (!DialogActiverCompte(currentUser.getCodeActivation(), MSG_CONFIRMATION))
        {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_CODE_ACTIVATION).showAndWait();
            return;
        }
        userService.activerCompte(currentUser);
        SceneHandler.openScene(SceneEnum.CLIENT_ACCEUIL);
    }
    
    // Button : acceuil.
    @FXML
    public void btn_acceuil_cxn_Click(ActionEvent event){
        SceneHandler.openScene(SceneEnum.ACCEUIL);
    }
    
    // Dialog : Activver compte.
    private boolean DialogActiverCompte(int codeActivation, String msg){
        Optional<String> result = Dialog.createTextInput(TITLE_ACTIVATION
            , TITLE_ACTIVATION
            , msg
            , ""
            ).showAndWait();
        if (result.isPresent()){
            return result.get().equals(Integer.toString(codeActivation));
        }
        return false;
    }
}
