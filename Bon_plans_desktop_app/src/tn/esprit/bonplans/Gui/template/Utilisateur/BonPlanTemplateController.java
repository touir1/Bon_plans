/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.template.Utilisateur;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import utils.CurrentSession;
import utils.FileUploadHandler;
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
    
    /*
    @FXML
    void OuvrirePageCategorie(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../Consulter/Categories.fxml"));
        Scene Scene= new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }
    */

    public static void main(String[] args) {
        launch(args);
    }    

    private void goToCategorie(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.VALIDER_MODIFIED_PLAN);
    }

    @FXML
    private void goPrevious(MouseEvent event) {
        SceneHandler.openPreviousScene();
    }

    @FXML
    private void openValidationScreensMenu(MouseEvent event) {
        
    }

    @FXML
    private void openCategorieListScene(MouseEvent event) {
        System.out.println("clicked");
        SceneHandler.openScene(SceneEnum.LISTE_CATEGORIE);
    }
}
