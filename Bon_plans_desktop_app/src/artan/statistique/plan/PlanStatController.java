/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.statistique.plan;

import artan.entities.Plan;
import artan.services.StatistiqueServices;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class PlanStatController extends Application implements Initializable {
    @FXML
    private Text titreLabel;
    @FXML
    private Text descriptionLabel;
    @FXML
    private Text prixLabel;
    @FXML
    private Text promoLabel;
    @FXML
    private Text qteLabel;
    @FXML
    private Text ddLabel;
    @FXML
    private Text dfLabel;
    @FXML
    private JFXButton btnPPC;
    @FXML
    private JFXButton btnPMC;
    @FXML
    private JFXButton btnPPA;
    
    private StatistiqueServices ss = new StatistiqueServices();
    private Plan resultat;
    @FXML
    private Label resultatLabel;
    @FXML
    private ImageView resultatImage;
    @FXML
    private JFXButton btnPPD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titreLabel.setText("");
        descriptionLabel.setText("");
        prixLabel.setText("");
        promoLabel.setText("");
        qteLabel.setText("");
        ddLabel.setText("");
        dfLabel.setText("");
    }  
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.STAT_PLAN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void btnPPCClicked(ActionEvent event) {
        System.out.println("PPC Clicked");
        resultat = ss.planlePlusCommenter();
        
        resultatLabel.setText("Plan le plus commenter : ");
        System.out.println("reultat = " + resultat);
        
        
        titreLabel.setText(resultat.getTitre());
        descriptionLabel.setText(resultat.getDescription());
        prixLabel.setText(resultat.getPrix() + "");
        promoLabel.setText(resultat.getPrixPromo() + "");
        qteLabel.setText(resultat.getQuantiteDisponible() + "");
        ddLabel.setText(resultat.getDateDebut().toString());
        dfLabel.setText(resultat.getDateFin().toString());
        
        Image image = new Image(resultat.getUrlPhoto());
        resultatImage.setImage(image);
        
        System.out.println("resultat = " + resultat);
    }

    @FXML
    private void btnPMCClicked(ActionEvent event) {
       System.out.println("PMC Clicked");
       resultat = ss.planLeMoinsCommenter();
       
       resultatLabel.setText("Plan le plus commenter : ");
        System.out.println("reultat = " + resultat);
        
        
        titreLabel.setText(resultat.getTitre());
        descriptionLabel.setText(resultat.getDescription());
        prixLabel.setText(resultat.getPrix() + "");
        promoLabel.setText(resultat.getPrixPromo() + "");
        qteLabel.setText(resultat.getQuantiteDisponible() + "");
        ddLabel.setText(resultat.getDateDebut().toString());
        dfLabel.setText(resultat.getDateFin().toString());
        
        Image image = new Image(resultat.getUrlPhoto());
        resultatImage.setImage(image);
        
       System.out.println("resultat = " + resultat);
    }

    @FXML
    private void btnPPAClicked(ActionEvent event) {
        System.out.println("PPA Clicked");
        resultat = ss.planLePlusAimer();
        
        resultatLabel.setText("Plan le plus commenter : ");
        System.out.println("reultat = " + resultat);
        
        
        titreLabel.setText(resultat.getTitre());
        descriptionLabel.setText(resultat.getDescription());
        prixLabel.setText(resultat.getPrix() + "");
        promoLabel.setText(resultat.getPrixPromo() + "");
        qteLabel.setText(resultat.getQuantiteDisponible() + "");
        ddLabel.setText(resultat.getDateDebut().toString());
        dfLabel.setText(resultat.getDateFin().toString());
        
        Image image = new Image(resultat.getUrlPhoto());
        resultatImage.setImage(image);
        
        System.out.println("resultat = " + resultat);
    }

    @FXML
    private void btnPPDClicked(ActionEvent event) {
        System.out.println("PPD Clicked");
        resultat = ss.planLePlusDetester();
        
        resultatLabel.setText("Plan le plus commenter : ");
        System.out.println("reultat = " + resultat);
        
        
        titreLabel.setText(resultat.getTitre());
        descriptionLabel.setText(resultat.getDescription());
        prixLabel.setText(resultat.getPrix() + "");
        promoLabel.setText(resultat.getPrixPromo() + "");
        qteLabel.setText(resultat.getQuantiteDisponible() + "");
        ddLabel.setText(resultat.getDateDebut().toString());
        dfLabel.setText(resultat.getDateFin().toString());
        
        Image image = new Image(resultat.getUrlPhoto());
        resultatImage.setImage(image);
        
        System.out.println("resultat = " + resultat);
    }
    
}
