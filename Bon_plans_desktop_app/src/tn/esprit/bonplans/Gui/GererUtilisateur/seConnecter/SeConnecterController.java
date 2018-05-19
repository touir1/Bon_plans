package tn.esprit.bonplans.Gui.GererUtilisateur.seConnecter;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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

/**
 * FXML Controller class
 *
 * @author KC
 */
public class SeConnecterController extends Application implements Initializable {
    
    private static final String TITLE_STAGE = "Bon plans";
    private static final String ERR = "Erreur";
    private static final String INFO = "Info";
    private static final String ERR_INPUT = "Entrée invalide";
    private static final String ERR_EMAIL = "Email incorrect.";
    private static final String ERR_PWD = "Mot de passe incorrect.";
    private static final String ERR_EMAIL_NONEXISTENT = "Email inexistant";
    private static final String ERR_EMAIL_PWD = "Email ou mot de passe incorrect.";
    private static final String ERR_ACTIVATION = "Code d'activation incorrecte.";
    private static final String PATH = "SeConnecter.fxml";
    private static final String TITLE_ACTIVATION = "Activation du compte";
    private static final String MSG_ACTIVATION_INPUT = "Veuillez introduire votre code d'activation";
    private static final String MSG_ACTIVATION_SENDED = "Un code d'activation à été envoyé à votre email.";
    
    
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
    public void btn_cxn_cxn_Click(ActionEvent event) throws NoSuchAlgorithmException {
        String email = txt_email_cxn.getText();
        String pwd = txt_pwd_cxn.getText();        
        
        // Controle de saisie.
        if (StringUtils.isEmpty(email)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_EMAIL).showAndWait();
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_PWD).showAndWait();
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
            Dialog.Error(ERR, ERR_INPUT, ERR_EMAIL_PWD).showAndWait();
            return;
        }
        
        // Compte n'est pas encore activé
        // Activation du compte, si le code d'activation valide.
        if (!currentUser.getIsActif()){
            if (!DialogActiverCompte(currentUser.getCodeActivation()))
            {
                Dialog.Error(ERR, ERR_INPUT, ERR_ACTIVATION).showAndWait();
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
    public void btn_signUp_cxn_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.SINSCRIRE);
    }
    
    // Button : mot de passe oublié.
    public void hln_pwd4get_cnx_Click(ActionEvent event){
        String email = txt_email_cxn.getText();
        
        // Controle de saisie sur l'émail.
        if (StringUtils.isEmpty(email)) {
            Dialog.Error(ERR, ERR_INPUT, ERR_EMAIL).showAndWait();
            return;
        }
        if (!userService.isExist(email)){
            Dialog.Info(INFO, null, ERR_EMAIL_NONEXISTENT).showAndWait();
            return;
        }
        
        Utilisateur utilisateur = userService.getUtilisateurByEmail(email);
        userService.deactiverCompte(utilisateur);
        userService.envoyerCodeActivation(utilisateur);
        Dialog.Info(INFO, null, MSG_ACTIVATION_SENDED).showAndWait();    
    }
    
    private boolean DialogActiverCompte(int codeActivation){
        Optional<String> result = Dialog.createTextInput(TITLE_ACTIVATION
            , TITLE_ACTIVATION
            , MSG_ACTIVATION_INPUT
            , ""
            ).showAndWait();
        if (result.isPresent()){
            return result.get().equals(Integer.toString(codeActivation));
        }
        return false;
    }
    
}
