/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererCategorie.Consulter;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.bonplans.Gui.GererCategorie.Ajouter.AjouterCategorieController;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;

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
      @FXML
    private TableView<Categorie_image> categories;
    private ObservableList<Categorie_image>data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data=FXCollections.observableArrayList();
        List<Categorie> ls=ic.selectAll();
        for(int i=0;i<ls.size();i++){
        Categorie_image ci=new Categorie_image(ls.get(i).getIdCategorie(),ls.get(i).getTitre(),new ImageView(new Image("file:///"+ls.get(i).getUrlPhoto(), true)));
             
        data.add(ci);
        }
        
        TableColumn tcC1 = new TableColumn<>("Image");
        tcC1.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
        tcC1.setPrefWidth(20.2);
        
        TableColumn tc2 = new TableColumn<>("Id categorie");
        tc2.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        TableColumn tc3 = new TableColumn<>("Titre");
        tc3.setCellValueFactory(new PropertyValueFactory<>("titre"));
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
    void onClickSupprimer(ActionEvent event) {
         Categorie_image ct=categories.getSelectionModel().getSelectedItem();
        System.out.println(ct.getIdCategorie());
        ic.remove(ct.getIdCategorie());
        categories.getItems().remove(ct);

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
