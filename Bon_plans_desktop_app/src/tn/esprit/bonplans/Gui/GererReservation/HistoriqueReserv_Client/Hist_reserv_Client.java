/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation.HistoriqueReserv_Client;

import com.jfoenix.controls.JFXButton;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;

/**
 *
 * @author SadfiAmine
 */
public class Hist_reserv_Client {
    private Date Date;
    private String Plan;
    private String Catégorie;
    private String Annanceur;
    private Hyperlink UrlBon;
    private Date DateFinPromo;
    private int IdReservation;
    private int IdPlan;
    

    public Hist_reserv_Client(Date Date, String Plan, String Catégorie, String Annanceur,Hyperlink UrlBon,Date DateFinPromo,int IdReservation,int IdPlan) {
        this.Date = Date;
        this.Plan = Plan;
        this.Catégorie = Catégorie;
        this.Annanceur = Annanceur;
        this.UrlBon=UrlBon;
        this.DateFinPromo=DateFinPromo;
        this.IdPlan=IdPlan;
        this.IdReservation=IdReservation;
    }

    public Date getDateFinPromo() {
        return DateFinPromo;
    }

    public void setDateFinPromo(Date DateFinPromo) {
        this.DateFinPromo = DateFinPromo;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String Plan) {
        this.Plan = Plan;
    }

    public String getCatégorie() {
        return Catégorie;
    }

    public void setCatégorie(String Catégorie) {
        this.Catégorie = Catégorie;
    }

    public String getAnnanceur() {
        return Annanceur;
    }

    public void setAnnanceur(String Annanceur) {
        this.Annanceur = Annanceur;
    }

  

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Hyperlink getUrlBon() {
        return UrlBon;
    }

    public void setUrlBon(Hyperlink UrlBon) {
        this.UrlBon = UrlBon;
    }

    public int getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(int IdReservation) {
        this.IdReservation = IdReservation;
    }

    public int getIdPlan() {
        return IdPlan;
    }

    public void setIdPlan(int IdPlan) {
        this.IdPlan = IdPlan;
    }

   
    
    
}
