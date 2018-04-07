/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererCategorie.Ajouter;

import com.gluonhq.charm.glisten.control.TextField;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class AjouterCategorieController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ICategorie ic =new CategorieImpl();
    List <String>lstfile;
    private  File f;
     @FXML
    private JFXTextField txtCategorie;
       @FXML
    private Label LblError;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstfile=new ArrayList<>();
        lstfile.add("*.png");
        lstfile.add("*.jpeg");
        lstfile.add("*.jpg");
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root= FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            
            
    }
       @FXML
    void onClickAjouter(ActionEvent event) {
        
        if ((f!=null)&&(txtCategorie.getText().length()!=0)) {
            LblError.setText("");
           List<Categorie> lc =ic.findOne("titre",txtCategorie.getText());
           if(!lc.isEmpty()){
           LblError.setText("Catégorie existe déja");
           }
           else{
            String urlph="file:///"+f.getAbsolutePath();
             Categorie c =new Categorie(1,txtCategorie.getText(),urlph);
            System.out.println(c.getTitre());
            System.out.println(c.getUrlPhoto());
            ic.save(c);
        }}
        else {
            LblError.setText("Veuillez remplir tous les champs");
        
        }
    }

    

    @FXML
    void onClickParcourir(ActionEvent event) {
        FileChooser fc =new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image files",lstfile));
        f =fc.showOpenDialog(null);
        if (f != null){
            boolean backgroundLoading = true;
            
            System.out.println(f.getPath());
            String urlph="file:///"+f.getAbsolutePath();
               Image i=new Image(urlph,backgroundLoading);
                image.setImage(i);
        }
        

    }
    public static void main(String[] args){launch(args);}
    
}
