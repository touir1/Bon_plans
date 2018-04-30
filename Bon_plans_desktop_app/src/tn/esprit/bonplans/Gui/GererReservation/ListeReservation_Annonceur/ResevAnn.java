/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererReservation.ListeReservation_Annonceur;

import java.util.Date;
import javafx.scene.control.Hyperlink;
import tn.esprit.bonplans.entity.Utilisateur;

/**
 *
 * @author SadfiAmine
 */
public class ResevAnn {
    private int idReservation;
    private Date date;
    private int Quantite;
    private Utilisateur Client;
    private Hyperlink urlBon;

    public ResevAnn(int id,Date date, int Quantite, Utilisateur Client, Hyperlink urlBon) {
        this.date = date;
        this.Quantite = Quantite;
        this.Client = Client;
        this.urlBon = urlBon;
        idReservation=id;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public Utilisateur getClient() {
        return Client;
    }

    public void setClient(Utilisateur Client) {
        this.Client = Client;
    }

    public Hyperlink getUrlBon() {
        return urlBon;
    }

    public void setUrlBon(Hyperlink urlBon) {
        this.urlBon = urlBon;
    }
    
    
}
