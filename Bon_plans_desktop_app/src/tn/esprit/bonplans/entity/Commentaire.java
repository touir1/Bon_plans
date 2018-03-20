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
    private int IdCommentaire;
    private String Texte;
    private Date Date;
    private int NbJaime;
    private int NbJaimePas;
    //
    private int IdClient;
    private int IdPlan;

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
    }

    public Commentaire() {
    }

    public int getIdCommentaire() {
        return IdCommentaire;
    }

    public void setIdCommentaire(int IdCommentaire) {
        this.IdCommentaire = IdCommentaire;
    }

    public String getTexte() {
        return Texte;
    }

    public void setTexte(String Texte) {
        this.Texte = Texte;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getNbJaime() {
        return NbJaime;
    }

    public void setNbJaime(int NbJaime) {
        this.NbJaime = NbJaime;
    }

    public int getNbJaimePas() {
        return NbJaimePas;
    }

    public void setNbJaimePas(int NbJaimePas) {
        this.NbJaimePas = NbJaimePas;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    public int getIdPlan() {
        return IdPlan;
    }

    public void setIdPlan(int IdPlan) {
        this.IdPlan = IdPlan;
    }
    
    
}
