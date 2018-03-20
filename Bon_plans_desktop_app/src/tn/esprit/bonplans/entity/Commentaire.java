/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

import java.util.Date;

/**
 *
 * @author SadfiAmine
 */
public class Commentaire {
<<<<<<< HEAD
    private int IdCommentaire;
    private String Texte;
    private Date Date;
    private int NbJaime;
    private int NbJaimePas;
=======
    private int idCommentaire;
    private String texte;
    private Date date;
    private int nbJaime;
    private int nbJaimePas;
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    //
    private int idClient;
    private int idPlan;

<<<<<<< HEAD
    public Commentaire(int IdCommentaire, String Texte, Date Date, int NbJaime, int NbJaimePas) {
        this.IdCommentaire = IdCommentaire;
        this.Texte = Texte;
        this.Date = Date;
        this.NbJaime = NbJaime;
        this.NbJaimePas = NbJaimePas;
    }

    public Commentaire(String Texte, Date Date, int NbJaime, int NbJaimePas) {
        this.Texte = Texte;
        this.Date = Date;
        this.NbJaime = NbJaime;
        this.NbJaimePas = NbJaimePas;
=======
    public Commentaire() {
    }

    public Commentaire(String texte, Date date, int nbJaime, int nbJaimePas, int idClient, int idPlan) {
        this.texte = texte;
        this.date = date;
        this.nbJaime = nbJaime;
        this.nbJaimePas = nbJaimePas;
        this.idClient = idClient;
        this.idPlan = idPlan;
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    }

    public Commentaire(int idCommentaire, String texte, Date date, int nbJaime, int nbJaimePas, int idClient, int idPlan) {
        this.idCommentaire = idCommentaire;
        this.texte = texte;
        this.date = date;
        this.nbJaime = nbJaime;
        this.nbJaimePas = nbJaimePas;
        this.idClient = idClient;
        this.idPlan = idPlan;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getTexte() {
<<<<<<< HEAD
        return Texte;
    }

    public void setTexte(String Texte) {
        this.Texte = Texte;
=======
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbJaime() {
        return nbJaime;
    }

    public void setNbJaime(int nbJaime) {
        this.nbJaime = nbJaime;
    }

    public int getNbJaimePas() {
        return nbJaimePas;
    }

    public void setNbJaimePas(int nbJaimePas) {
        this.nbJaimePas = nbJaimePas;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", texte=" + texte + ", date=" + date + ", nbJaime=" + nbJaime + ", nbJaimePas=" + nbJaimePas + ", idClient=" + idClient + ", idPlan=" + idPlan + '}';
    }

    
    
    
}
