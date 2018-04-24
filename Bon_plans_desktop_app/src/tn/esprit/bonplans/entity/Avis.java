/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

/**
 *
 * @author touir
 */
public class Avis {
    
    private int idAvis;
    private int avis;
    private int note;
    
    //foreign keys
    private int idPlan;
    private int idUtilisateur;

    public Avis() {
    }

    public Avis(int idAvis, int avis, int note, int idPlan, int idUtilisateur) {
        this.idAvis = idAvis;
        this.avis = avis;
        this.note = note;
        this.idPlan = idPlan;
        this.idUtilisateur = idUtilisateur;
    }

    public Avis(int avis, int note, int idPlan, int idUtilisateur) {
        this.avis = avis;
        this.note = note;
        this.idPlan = idPlan;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Avis{" + "idAvis=" + idAvis + ", avis=" + avis + ", note=" + note + ", idPlan=" + idPlan + ", idUtilisateur=" + idUtilisateur + '}';
    }
    
    
    
}
