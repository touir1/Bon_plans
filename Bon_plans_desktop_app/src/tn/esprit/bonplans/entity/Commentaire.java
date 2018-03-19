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
    private int idCommentaire;
    private int texte;
    private Date date;
    private int nbJaime;
    private int nbJaimePas;
    //
    private int idClient;
    private int idPlan;

    public Commentaire() {
    }

    public Commentaire(int texte, Date date, int nbJaime, int nbJaimePas, int idClient, int idPlan) {
        this.texte = texte;
        this.date = date;
        this.nbJaime = nbJaime;
        this.nbJaimePas = nbJaimePas;
        this.idClient = idClient;
        this.idPlan = idPlan;
    }

    public Commentaire(int idCommentaire, int texte, Date date, int nbJaime, int nbJaimePas, int idClient, int idPlan) {
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

    public int getTexte() {
        return texte;
    }

    public void setTexte(int texte) {
        this.texte = texte;
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

    
    
    
}
