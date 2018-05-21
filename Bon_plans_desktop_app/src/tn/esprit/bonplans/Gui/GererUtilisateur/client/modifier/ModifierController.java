/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.client.modifier;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_CONST_PWD;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_EMAIL;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_NOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.ERR_INPUT_PRENOM;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.INFO;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.MSG_SUCC_UPDATE;
import static tn.esprit.bonplans.Gui.GererUtilisateur.Constantes.TITLE_STAGE;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.Dialog;
import utils.FileUploadHandler;
import utils.StringUtils;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class ModifierController extends Application implements Initializable {

    private static final String PATH = "Modifier.fxml";
    
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField txt_ville;
    @FXML
    private JFXTextField txt_adresse;
    @FXML
    private JFXPasswordField txt_pwd;
    @FXML
    private ImageView image;
    
    private Utilisateur currentUser;
    private File f;
    private List<String> lstfile;
    private IUtilisateur userService;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new UtilisateurImpl();
        lstfile = new ArrayList<>();
        lstfile.add("*.png");
        lstfile.add("*.jpeg");
        lstfile.add("*.jpg");
        initFields();
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE_STAGE);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    public void initFields(){
        currentUser = CurrentSession.getUtilisateur();
        if (currentUser != null){
            txt_email.setText(currentUser.getEmail());
            txt_nom.setText(currentUser.getNom());
            txt_prenom.setText(currentUser.getPrenom());
            txt_adresse.setText(currentUser.getAdresse());
            txt_ville.setText(currentUser.getVille());
            if (!StringUtils.isEmpty(currentUser.getUrlphoto())){
                image.setImage(new Image(currentUser.getUrlphoto()));
            }
        }
    }
    
    @FXML
    public void btn_parcourir_Click(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", lstfile));
        f = fc.showOpenDialog(null);
        if (f != null) {
            boolean backgroundLoading = true;
            System.out.println(f.getPath());
            String urlph = "file:///" + f.getAbsolutePath();
            Image i = new Image(urlph, backgroundLoading);
            image.setImage(i);
        }
    }
    
    @FXML
    public void btn_valider_Click(ActionEvent event) {
        String email = txt_email.getText();
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String pwd = txt_pwd.getText();
        
        if (currentUser != null){
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
            if (!StringUtils.isEmpty(pwd) && !StringUtils.isValidPwd(pwd)) {
                Dialog.Error(ERR, ERR_INPUT, ERR_CONST_PWD).showAndWait();
                return;
            }
            
            currentUser.setEmail(email);
            currentUser.setNom(nom);
            currentUser.setPrenom(prenom);
            if (!StringUtils.isEmpty(pwd)) {
                currentUser.setMdp(pwd);
            }
            currentUser.setAdresse(txt_adresse.getText());
            currentUser.setVille(txt_ville.getText());
            try {
                currentUser.setUrlphoto(FileUploadHandler.getFileURL(f, Utilisateur.class, currentUser.getIdUtilisateur()));
                FileUploadHandler.uploadFile(Utilisateur.class, currentUser.getIdUtilisateur(), f);
            } catch (Exception e) {
            }
            currentUser = userService.updateUser(currentUser, !StringUtils.isEmpty(pwd));
            Dialog.Info(INFO, null, MSG_SUCC_UPDATE).showAndWait();
        }
    }

    @FXML
    public void btn_annuler_Click(ActionEvent event){
        initFields();
    }
}
