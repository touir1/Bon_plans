/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererUtilisateur.client.acceuil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author KC
 */
public class AcceuilController extends Application implements Initializable {

    @FXML
    private Label TitreCategorie1;
    
    @FXML
    private ImageView ImgC2P1;

    @FXML
    private Label TitreC2P1;
    
    @FXML
    private ImageView ImgC1P3;

    @FXML
    private Label TitreC1P3;
    
    @FXML
    private ImageView ImgC1P2;

    @FXML
    private Label TitreC1P2;

    @FXML
    private Label TitreCategorie2;
    
    @FXML
    private ImageView ImgC1P1;

    @FXML
    private Label TitreC1P1;
    
    @FXML
    private ImageView ImgC2P2;

    @FXML
    private Label TitreC2P2;

    @FXML
    private ImageView ImgC2P3;

    @FXML
    private Label TitreC2P3;


    @FXML
    private Label TitreCategorie3;

    @FXML
    private ImageView ImgC3P1;

    @FXML
    private Label TitreC3P1;


    @FXML
    private ImageView ImgC3P2;

    @FXML
    private Label TitreC3P2;
    

    @FXML
    private ImageView ImgC3P3;

    @FXML
    private Label TitreC3P3;
   
    
    private ICategorie ICategorie =new CategorieImpl();
    private IPlan IPlan= new PlanImpl();
    List<Plan> planCategorie1Choisie= new ArrayList();
    List<Plan> planCategorie2Choisie=new ArrayList();
    List<Plan> planCategorie3Choisie=new ArrayList();
    public static Plan planAAfficher;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categorie> ListCategorie =ICategorie.selectAll();
        List<Categorie> ListCategorieChoisie =new ArrayList<>();
        Random rand= new Random();
        if(!ListCategorie.isEmpty()){
            for(int i=0;i<3;i++){
                int nbAleatoire= rand.nextInt(ListCategorie.size());
                if(!ListCategorieChoisie.contains(ListCategorie.get(nbAleatoire)))
                    ListCategorieChoisie.add(ListCategorie.get(nbAleatoire));
                else
                    i--;
            }}
        TitreCategorie1.setText(ListCategorieChoisie.get(0).getTitre());
        TitreCategorie2.setText(ListCategorieChoisie.get(1).getTitre());
        TitreCategorie3.setText(ListCategorieChoisie.get(2).getTitre());
        List<Plan> planCategorie1 =IPlan.findOne("idCategorie",ListCategorieChoisie.get(0).getIdCategorie());
        List<Plan> planCategorie2 =IPlan.findOne("idCategorie",ListCategorieChoisie.get(1).getIdCategorie());
        List<Plan> planCategorie3 =IPlan.findOne("idCategorie",ListCategorieChoisie.get(2).getIdCategorie());
        
        if(!planCategorie1.isEmpty()){
            for(int i=0;i<3;i++){
            int nbAleatoire= rand.nextInt(planCategorie1.size());
            if(!planCategorie1Choisie.contains(planCategorie1.get(nbAleatoire)))
                planCategorie1Choisie.add(planCategorie1.get(nbAleatoire));
            else
                i--;
        }}
        if(!planCategorie2.isEmpty()){
            for(int i=0;i<3;i++){
                int nbAleatoire= rand.nextInt(planCategorie2.size());
                if(!planCategorie2Choisie.contains(planCategorie2.get(nbAleatoire)))
                    planCategorie2Choisie.add(planCategorie2.get(nbAleatoire));
                else
                    i--;
        }}
        if(!planCategorie3.isEmpty()){
            for(int i=0;i<3;i++){
                int nbAleatoire= rand.nextInt(planCategorie1.size());
                if(!planCategorie3Choisie.contains(planCategorie3.get(nbAleatoire)))
                    planCategorie3Choisie.add(planCategorie3.get(nbAleatoire));
                else
                    i--;
            }
        }
        //Affichage categorie 1
        ImgC1P1.setImage(new Image(planCategorie1Choisie.get(0).getUrlPhoto(),true));
        System.out.println(planCategorie1Choisie.get(0).getUrlPhoto());
        TitreC1P1.setText(planCategorie1Choisie.get(0).getTitre());
        TitreC1P1.setWrapText(true);
        ImgC1P2.setImage(new Image(planCategorie1Choisie.get(1).getUrlPhoto(),true));
        TitreC1P2.setText(planCategorie1Choisie.get(1).getTitre());
        TitreC1P2.setWrapText(true);
        ImgC1P3.setImage(new Image(planCategorie1Choisie.get(2).getUrlPhoto(),true));
        TitreC1P3.setText(planCategorie1Choisie.get(2).getTitre());
        TitreC1P3.setWrapText(true);
        //Affichage categorie 2
        ImgC2P1.setImage(new Image(planCategorie2Choisie.get(0).getUrlPhoto(),true));
        TitreC2P1.setText(planCategorie2Choisie.get(0).getTitre());
        TitreC2P1.setWrapText(true);
        ImgC2P2.setImage(new Image(planCategorie2Choisie.get(1).getUrlPhoto(),true));
        TitreC2P2.setText(planCategorie2Choisie.get(1).getTitre());
        TitreC2P2.setWrapText(true);
        ImgC2P3.setImage(new Image(planCategorie2Choisie.get(2).getUrlPhoto(),true));
        TitreC2P3.setText(planCategorie2Choisie.get(2).getTitre());
        TitreC2P3.setWrapText(true);
         //Affichage categorie 2
        ImgC3P1.setImage(new Image(planCategorie3Choisie.get(0).getUrlPhoto(),true));
        TitreC3P1.setText(planCategorie3Choisie.get(0).getTitre());
        TitreC3P1.setWrapText(true);
        ImgC3P2.setImage(new Image(planCategorie3Choisie.get(1).getUrlPhoto(),true));
        TitreC3P2.setText(planCategorie3Choisie.get(1).getTitre());
        TitreC3P2.setWrapText(true);
        ImgC3P3.setImage(new Image(planCategorie3Choisie.get(2).getUrlPhoto(),true));
        TitreC3P3.setText(planCategorie3Choisie.get(2).getTitre());
        TitreC3P3.setWrapText(true);
    }    
   
    
    @FXML
    void test(MouseEvent event) throws IOException {
        if(event.getSource().toString().contains("Categorie1plan1")){
            planAAfficher=planCategorie1Choisie.get(0);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie1plan2")){
            planAAfficher=planCategorie1Choisie.get(1);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie1plan3")){
            planAAfficher=planCategorie1Choisie.get(2);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie2plan1")){
            planAAfficher=planCategorie2Choisie.get(0);
            System.out.println(planAAfficher);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie2plan2")){
            planAAfficher=planCategorie2Choisie.get(1);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie2plan3")){
            planAAfficher=planCategorie2Choisie.get(2);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie3plan1")){
            planAAfficher=planCategorie3Choisie.get(0);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else if(event.getSource().toString().contains("Categorie3plan2")){
            planAAfficher=planCategorie3Choisie.get(1);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        else{
            planAAfficher=planCategorie3Choisie.get(2);
            CurrentSession.addData("openedPlan", planAAfficher);
        }
        SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
//        
    }

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene Scene= new Scene(root);
        primaryStage.setScene(Scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }

}
