/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.awt.event.InputMethodEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Plan;
import utils.entity.EnumValidation;

/**
 * FXML Controller class
 *
 * @author SadfiAmine
 */
public class TestReservationController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Label Lbl;
     @FXML
    private JFXTextField txtnumber;
      @FXML
    private Label Total;
      private Plan pl;
    @FXML
    private Label lblPrixUnitaire;
    private int TestValidation=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Date dateDebut= new Date();
        Date dateFin= new Date();
        pl = new Plan(1, "spa et massage","xxxx","c:/fawzi",50.0, 20.0,10, dateDebut, dateFin, 10, EnumValidation.validee, 11, 5, 20, 10, 50,1);
        System.out.println(pl.getPrixPromo().toString());
       lblPrixUnitaire.setText("Prix unitaire : "+pl.getPrixPromo());
       
       txtnumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^\\d+$")) {
                    Lbl.setText("Vous devez inserer un nombre ");
                    TestValidation=1;
                    Total.setText("");
                }
               else{
                Lbl.setText("");
                 Total.setText("");
                TestValidation=0;
                Double total =Double.parseDouble(newValue)*pl.getPrixPromo();
                Total.setText("Total : "+total);
                
                
                }
            }
        });
    }    
     @FXML
    void OnClickReserver(ActionEvent event) {
         Document Doc = new Document(new Rectangle(250,250));
         try {
             PdfWriter.getInstance(Doc, new FileOutputStream("C:\\wamp64\\www\\Pdf\\IdClient"+pl.getIdClient()+"IdAnnance"+pl.getIdPlan()+".pdf"));
             Doc.open();
             Doc.add(new Paragraph("     Bon de réservation"));
             Doc.add(new Paragraph("     "+pl.getTitre()));
             Doc.add(new Paragraph("     ------------------------------"));
             Doc.add(new Paragraph("     Nombre de place     "+txtnumber.getText()));
             Doc.add(new Paragraph("     -------------------------------"));
             Doc.add(new Paragraph("     prix Promo     "+pl.getPrixPromo()+" DNT"  ));
             Doc.add(new Paragraph("     ------------------------------" ));
             Doc.add(new Paragraph("     Total              "+Double.parseDouble(txtnumber.getText())*pl.getPrixPromo()+" DNT"));
             Doc.close();
             Desktop.getDesktop().open(new File("C:\\wamp64\\www\\Pdf\\IdClient"+pl.getIdClient()+"IdAnnance"+pl.getIdPlan()+".pdf"));
                     } catch (FileNotFoundException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (DocumentException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
   

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("TestReservation.fxml"));
           
            Scene Scene= new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
    }
    public static void main(String[] args){launch(args);}
}
