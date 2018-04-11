/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererCategorie.Modifier;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.stage.Stage;
import static tn.esprit.bonplans.Gui.GererCategorie.Consulter.CategoriesController.idCategorieAModifiee;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import utils.FileUploadHandler;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class ModifierCategorieController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    List <String>lstfile;
    private ICategorie ic= new CategorieImpl();
    private  File f;
     @FXML
    private ImageView image;
     

    @FXML
    private JFXTextField txtCategorie;

    @FXML
    private Label LblError;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List <Categorie> ls=ic.findOne("idCategorie", idCategorieAModifiee);
        System.out.println("hello");
        txtCategorie.setText(ls.get(0).getTitre());
      Image i = new Image("file:///"+ls.get(0).getUrlPhoto(), true);
        image.setImage(i);
        f=new File(ls.get(0).getUrlPhoto());
        // TODO
          lstfile=new ArrayList<>();
        lstfile.add("*.png");
        lstfile.add("*.jpeg");
        lstfile.add("*.jpg");
        ///////////////
    
    }    

      @FXML
    void onClickModifier(ActionEvent event) {
         if ((f!=null)&&(txtCategorie.getText().length()!=0)) {
            LblError.setText("");
           //List<Categorie> lc=new ArrayList<Categorie>() ;
            
            //lc= ic.findOne("titre",txtCategorie.getText());
            //if(lc!=null){
              //LblError.setText("Catégorie existe déja");
            //}
           //else{
            Categorie c = new Categorie();
            c.setIdCategorie(idCategorieAModifiee);
            c.setTitre(txtCategorie.getText());
            c.setUrlPhoto(FileUploadHandler.getFileURL(f, Categorie.class, c.getIdCategorie()));
            System.out.println(c);

            if(FileUploadHandler.uploadFile(Categorie.class, c.getIdCategorie(), f)){
                c = ic.update(c);
                System.out.println("Upload success");
                System.out.println(c);
            }
            else{
                System.out.println("Upload echoué");
                LblError.setText("Erreur serveur lors de l'upload");
            }
        //}
    }
        else {
            LblError.setText("Veuillez remplir tous les champs");
        
        }

    }



    @FXML
    void onClickParcourir(ActionEvent event) {
         FileChooser fc =new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files",lstfile));
        f =fc.showOpenDialog(null);
        if (f != null){
            boolean backgroundLoading = true;
            
            System.out.println(f.getPath());
            String urlph="file:///"+f.getAbsolutePath();
               Image i=new Image(urlph,backgroundLoading);
                image.setImage(i);
        }

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root= FXMLLoader.load(getClass().getResource("ModifierCategorie.fxml"));
           
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            
            
    }
      public static void main(String[] args){launch(args);}
}
