package tn.esprit.bonplans.Gui.GererUtilisateur.seConnecter;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.Dialog;
import utils.StringUtils;
import utils.entity.EnumGroupe;
import utils.Error;
import utils.SceneEnum;
import utils.SceneHandler;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_EMAIL;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_EMAIL_NONEXISTENT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_EMAIL_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_CODE_ACTIVATION;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_CONST_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.INFO;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_SUCC_CHANGER_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MIN_LENGTH_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_ACTIVATION_INPUT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_ACTIVATION_WITH_MAIL;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_PWD_OUBLIE;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_ACTIVATION;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_STAGE;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SeConnecterController extends Application implements Initializable {
    
    private static final String PATH = "SeConnecter.fxml";
    private static final String TITLE_PWD_OUBLIE = "Mot de passe oublié";
    
    private IUtilisateur userService;
    
    @FXML
    private JFXTextField txt_email_cxn;
    @FXML
    private JFXPasswordField txt_pwd_cxn;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE_STAGE);
        primaryStage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UtilisateurImpl();
    }
    
    // Button : se connecter.
    @FXML
    public void btn_cxn_cxn_Click(ActionEvent event) throws NoSuchAlgorithmException {
        String email = txt_email_cxn.getText();
        String pwd = txt_pwd_cxn.getText();        
        
        // Controle de saisie.
        if (StringUtils.isEmpty(email)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_EMAIL).showAndWait();
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_PWD).showAndWait();
            return;
        }
        
        Error error = new Error();
        Utilisateur currentUser = userService.connecter(email, pwd, error); 
        
        // Exeception lors l'authentification.
        if (!error.getMessage().isEmpty()) {
            Dialog.Error(ERR, ERR_INPUT, error.getMessage()).showAndWait();
            return;
        }
        // Utilisateur inéxistant.
        if (currentUser == null) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_EMAIL_PWD).showAndWait();
            return;
        }
        
        // Compte n'est pas encore activé
        // Activation du compte, si le code d'activation valide.
        if (!currentUser.getIsActif()){
            if (!DialogActiverCompte(currentUser.getCodeActivation(), MSG_ACTIVATION_INPUT))
            {
                Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_CODE_ACTIVATION).showAndWait();
                return;
            }
            userService.activerCompte(currentUser);
        }
        
        // Mise à jout de l'utilisateur courant.
        CurrentSession.setUtilisateur(currentUser);
        
        // Rédirection des interfaces selon le groupe de l'utilisateur.
        if (currentUser.getIdGroupe() == EnumGroupe.Client.getValue()){
            Dialog.Info(null, "Client", "client").showAndWait();
        }
        else {
            Dialog.Info(null, "admin", "admin").showAndWait();
        }    
    }

    // Button : s'inscrire.
    @FXML
    public void btn_signUp_cxn_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.SINSCRIRE);
    }
    
    // Button : mot de passe oublié.
    @FXML
    public void hln_pwd4get_cnx_Click(ActionEvent event){
        String email = txt_email_cxn.getText();
        
        // Controle de saisie sur l'émail.
        if (StringUtils.isEmpty(email)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_EMAIL).showAndWait();
            return;
        }
        
        //Email inexistant.
        if (!userService.isExist(email)){
            Dialog.Info(INFO, null, ERR_EMAIL_NONEXISTENT).showAndWait();
            return;
        }
        
        Utilisateur currentUser = userService.getUtilisateurByEmail(email);
        
        // Déactivation temporaire du compte et l'envoi d'un mail d'activation.
        userService.deactiverCompte(currentUser);
        userService.envoyerCodeActivation(currentUser);
       
        
        // Activation du compte.
        if (!DialogActiverCompte(currentUser.getCodeActivation(), MSG_ACTIVATION_WITH_MAIL))
        {
            Dialog.Error(ERR, ERR_INPUT, ERR_INPUT_CODE_ACTIVATION).showAndWait();
            return;
        }
        
        // Changement du mot du passe.
        String newPwd = DialogChangerPwd(false);
        if (newPwd == null){
            return;     
        }
        
        userService.updatePwd(currentUser, newPwd);
        Dialog.Info(INFO, null, MSG_SUCC_CHANGER_PWD).showAndWait();
        
    }
    
    // Button : acceuil.
    @FXML
    public void btn_acceuil_cxn_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.ACCEUIL);
    }
    
    // Dialog : Activer compte.
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
    
    // Dialog : Changer mot de passe.
    private String DialogChangerPwd (boolean withError){
        Optional<String> result = Dialog.createTextInput(TITLE_PWD_OUBLIE
            , TITLE_PWD_OUBLIE
            , withError ? ERR_CONST_PWD : MSG_PWD_OUBLIE
            , ""
            ).showAndWait();
        if (result.isPresent()){
            if (result.get().length() < MIN_LENGTH_PWD)
                return DialogChangerPwd (true);
            return result.get();
        }
        return null;
    }

}
