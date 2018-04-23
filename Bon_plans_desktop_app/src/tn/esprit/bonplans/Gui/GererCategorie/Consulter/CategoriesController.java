/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererCategorie.Consulter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.bonplans.Gui.GererCategorie.Ajouter.AjouterCategorieController;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class CategoriesController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static int idCategorieAModifiee;
    private ICategorie ic =new CategorieImpl();
    private IPlan ip= new PlanImpl();
     @FXML
    private Label LblError;
      @FXML
    private TableView<Categorie_image> categories;
    private ObservableList<Categorie_image>data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data=FXCollections.observableArrayList();
        List<Categorie> ls=ic.selectAll();
        for(int i=0;i<ls.size();i++){
            List <Plan>ListPlanParCatégorie=new ArrayList<>();
            ListPlanParCatégorie=ip.findOne("idCategorie", ls.get(i).getIdCategorie());
            System.err.println(ls.get(i).getUrlPhoto());
           int nbPlan=ListPlanParCatégorie.size();
            ImageView im =new ImageView(new Image(ls.get(i).getUrlPhoto(),true));
            
            im.setFitHeight(30);
            im.setFitWidth(35);
        Categorie_image ci=new Categorie_image(ls.get(i).getIdCategorie(),ls.get(i).getTitre(),im,nbPlan);
             
        data.add(ci);
        }
        
        TableColumn tcC1 = new TableColumn<>("Image");
        tcC1.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
        TableColumn tc3 = new TableColumn<>("Nombre de plan associé à cette catégorie");
        tc3.setCellValueFactory(new PropertyValueFactory<>("nbplanparCategorie"));
        TableColumn tc2 = new TableColumn<>("Titre");
        tc2.setCellValueFactory(new PropertyValueFactory<>("titre"));
        categories.getColumns().add(tcC1);
        categories.getColumns().add(tc2);
        categories.getColumns().add(tc3);
        categories.setItems(data);
        
        
    }    
    
    
    @FXML
    void onClickModifier(ActionEvent event) throws Exception {
      Categorie_image ct=categories.getSelectionModel().getSelectedItem();
        System.out.println(ct.getIdCategorie());
        idCategorieAModifiee=ct.getIdCategorie();
         Parent root= FXMLLoader.load(getClass().getResource("../Modifier/ModifierCategorie.fxml"));
          Scene Scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Scene);
          window.show();
           
    }
    
      @FXML
    void OuvrirePageAjouterCategorie(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../Ajouter/AjouterCategorie.fxml"));
          Scene Scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Scene);
          window.show();
    }

    @FXML
    void onClickSupprimer(ActionEvent event) {
         Categorie_image ct=categories.getSelectionModel().getSelectedItem();
         if(ct.getNbplanparCategorie()==0){
        System.out.println(ct.getIdCategorie());
        ic.remove(ct.getIdCategorie());
        categories.getItems().remove(ct);}
         else{
            LblError.setText("Impossible de supprimer cette catégorie");
         }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
           Parent root= FXMLLoader.load(getClass().getResource("Categories.fxml"));
           
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            
            
    }
    
      public static void main(String[] args){launch(args);}
}
