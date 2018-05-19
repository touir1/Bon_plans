/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.InputMethodEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Reservation;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.IPlan;

import tn.esprit.bonplans.service.IReservation;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.PlanImpl;

import tn.esprit.bonplans.service.implementation.ReservationImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
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
    private IUtilisateur IUtilisateur=new UtilisateurImpl();
    private IReservation Ireservation= new ReservationImpl();
    private IPlan iPlan= new PlanImpl();
    
     @FXML
    private Label Lbl;
     @FXML
    private JFXTextField txtnumber;
      @FXML
    private Label Total;
      private Plan pl;
      private List<Utilisateur> Annonceur = new ArrayList<>();
      private Utilisateur Client;
    @FXML
    private Label lblPrixUnitaire;
    private int TestValidation=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CurrentSession.setUtilisateur(IUtilisateur.getByID(12));
        Date dateDebut= new Date();
        Date dateFin= new Date();
        CurrentSession.getUtilisateur();
        //pl = (Plan) CurrentSession.getData("openedPlan");
        pl=iPlan.getByID(7);
        Annonceur=IUtilisateur.findOne("idUtilisateur", pl.getIdAnnonceur());
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
    void OnClickReserver(ActionEvent event) throws IOException {
         Document Doc = new Document(new Rectangle(300,500));
         try {
             PdfWriter.getInstance(Doc, new FileOutputStream("C:\\wamp64\\www\\bon_plans_api\\uploads\\Reservation_IdClient"+pl.getIdAnnonceur()+"IdAnnance"+pl.getIdPlan()+".pdf"));
             Doc.open();
             Image im=Image.getInstance("http://localhost/bon_plans_api/uploads/Logo.png");
             im.scaleAbsoluteWidth(100);
             im.scaleAbsoluteHeight(100);
            // im.setAlignment(Image.ALIGN_CENTER);
             im.setAlignment(Image.TITLE);
              Doc.add(im);
             Paragraph AdresseMail= new Paragraph(new Phrase("Contact@BonsPlans.tn",FontFactory.getFont("Comic Sans MS",7)));
             AdresseMail.setAlignment(Element.ALIGN_CENTER);
             Doc.add(AdresseMail);
             Paragraph Tel= new Paragraph(new Phrase("+216 71 777 333",FontFactory.getFont("Comic Sans MS",7)));
             Tel.setAlignment(Element.ALIGN_CENTER);
             Doc.add(Tel);
            
             Paragraph Titre= new Paragraph(new Phrase("Bon de réservation", FontFactory.getFont("Comic Sans MS",15)));
             Titre.setIndentationLeft(50);
             Titre.setSpacingBefore(5);
             Titre.setSpacingAfter(26);
             Date date= new Date();
            Paragraph DateNow= new Paragraph(new Phrase(date.toString(),FontFactory.getFont("Comic Sans MS",10)));
             DateNow.setAlignment(Element.SECTION);
             DateNow.setSpacingAfter(5);
            Doc.add(Titre);
             Doc.add(DateNow);
             Paragraph NomAnnonceur= new Paragraph(new Phrase("Annonceur: "+Annonceur.get(0).getNom(),FontFactory.getFont("Comic Sans MS",10)));
             NomAnnonceur.setAlignment(Element.SECTION);
             NomAnnonceur.setSpacingAfter(25);
             Doc.add(NomAnnonceur);
      
             PdfPTable table= new PdfPTable(3);
             PdfPCell cell;
             cell= new PdfPCell(new Phrase("Libellé", FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.GRAY);
             table.addCell(cell);
             cell= new PdfPCell(new Phrase("Nombre de place", FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.GRAY);
             table.addCell(cell);
             cell= new PdfPCell(new Phrase("Prix unitaire", FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.GRAY);
             table.addCell(cell);
             /////////////////////////////////////
              cell= new PdfPCell(new Phrase(pl.getTitre(), FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.WHITE);
             table.addCell(cell);
             cell= new PdfPCell(new Phrase(txtnumber.getText(), FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.WHITE);
             table.addCell(cell);
             cell= new PdfPCell(new Phrase(pl.getPrixPromo().toString(), FontFactory.getFont("Comic Sans MS",10)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(BaseColor.WHITE);
             table.addCell(cell);
             Doc.add(table);
             Paragraph Total= new Paragraph(new Phrase("TOTAL : "+Double.parseDouble(txtnumber.getText())*pl.getPrixPromo()+" DNT",FontFactory.getFont("Comic Sans MS",11)));
             Total.setAlignment(Element.SECTION);
             Total.setSpacingBefore(30);
             Total.setSpacingAfter(17);
             Doc.add(Total);
             Paragraph Footer= new Paragraph(new Phrase("En cas de réclamation vous pouvez nous contacter sur notre site www.BonsPlans.tn ",FontFactory.getFont("Comic Sans MS",10)));
             Footer.setAlignment(Element.ALIGN_CENTER);
             Footer.setSpacingBefore(25);
             Doc.add(Footer);
             Paragraph Footer1= new Paragraph(new Phrase("Bons Plans vous remercie pour votre confiance ",FontFactory.getFont("Comic Sans MS",9)));
             Footer1.setAlignment(Element.ALIGN_CENTER);
            
             Doc.add(Footer1);
             Doc.close();
             //////Insertion dans la bd 
             System.err.println(CurrentSession.getUtilisateur().getIdUtilisateur());
             Reservation r = new Reservation(date,"http://localhost/bon_plans_api/uploads/Reservation_IdClient"+pl.getIdAnnonceur()+"IdAnnance"+pl.getIdPlan()+".pdf",CurrentSession.getUtilisateur().getIdUtilisateur(), pl.getIdPlan(),Integer.parseInt(txtnumber.getText()));
             System.out.println(r);
             Ireservation.save(r);
             //diminuer le nombre des places dispo 
             pl.setNbPlaceDispo(pl.getNbPlaceDispo()-Integer.parseInt(txtnumber.getText()));
             iPlan.update(pl);
             Desktop.getDesktop().browse(new URI("http://localhost/bon_plans_api/uploads/Reservation_IdClient"+pl.getIdAnnonceur()+"IdAnnance"+pl.getIdPlan()+".pdf"));
                     } catch (FileNotFoundException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (DocumentException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (URISyntaxException ex) {
             Logger.getLogger(TestReservationController.class.getName()).log(Level.SEVERE, null, ex);
         }
         Parent root= FXMLLoader.load(getClass().getResource("./HistoriqueReserv_Client/Historique_ReservClient.fxml"));
          Scene Scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Scene);
          window.show();
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


