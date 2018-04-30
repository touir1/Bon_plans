/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.template.Utilisateur;

import com.gluonhq.charm.glisten.control.Avatar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class BonPlanTemplateController extends Application implements Initializable {

    private final String FXML_FILE_PATH = "BonPlanTemplate.fxml";
    private final String LOGO_PATH = "./resources/images/Logo.png";
    private final String TITLE = "bon plans";
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane Menu;
    @FXML
    private Label screenTitle;
    @FXML
    private Avatar userImage;
    @FXML
    private Label username;
    @FXML
    private Hyperlink btnLogout;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CurrentSession.addData("screenTitleLabel", screenTitle);
    }    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE_PATH));
        
        Scene Scene = new Scene(root);
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image(LOGO_PATH));
        primaryStage.setScene(Scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }    

    private void goToCategorie(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.LISTE_CATEGORIE);
    }

    @FXML
    private void goPrevious(MouseEvent event) {
        SceneHandler.openPreviousScene();
    }

    @FXML
    private void openValidationScreensMenu(MouseEvent event) {
        SceneHandler.openScene(SceneEnum.VALIDER_NEW_PLAN);
    }

    @FXML
    private void openCategorieListScene(MouseEvent event) {
        System.out.println("clicked");
        SceneHandler.openScene(SceneEnum.LISTE_CATEGORIE);
    }

    @FXML
    private void logout(MouseEvent event) {
        SceneHandler.clearSceneHistory();
        SceneHandler.openScene(SceneEnum.SE_CONNECTER, false);
    }
}
