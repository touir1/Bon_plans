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

public class Plan {
    private int IdPlan;
    private String Titre ;
    private String Description;
    private  String UrlPhoto;
    private  Double PrixInitial;
    private Double PrixPromo;
    private int NbplaceTotal;
    private Date DateDebut;
    private Date DateFin;
    private int NbPlaceDispo;
    private EnumValidation statut;
    private int NbJaime ;
    private int NbJaimePas;
    private int Note ;
    // 
    private int IdAnnonceur;
    private int idCategorie;

    public Plan() {
    }

    public Plan(int IdPlan, String Titre, String Description, String UrlPhoto, Double PrixInitial, Double PrixPromo, int NbplaceTotal, Date DateDebut, Date DateFin, int NbPlaceDispo, EnumValidation statut, int NbJaime, int NbJaimePas, int Note) {
        this.IdPlan = IdPlan;
        this.Titre = Titre;
        this.Description = Description;
        this.UrlPhoto = UrlPhoto;
        this.PrixInitial = PrixInitial;
        this.PrixPromo = PrixPromo;
        this.NbplaceTotal = NbplaceTotal;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaceDispo = NbPlaceDispo;
        this.statut = statut;
        this.NbJaime = NbJaime;
        this.NbJaimePas = NbJaimePas;
        this.Note = Note;
    }

    public Plan(String Titre, String Description, String UrlPhoto, Double PrixInitial, Double PrixPromo, int NbplaceTotal, Date DateDebut, Date DateFin, int NbPlaceDispo, EnumValidation statut, int NbJaime, int NbJaimePas, int Note) {
        this.Titre = Titre;
        this.Description = Description;
        this.UrlPhoto = UrlPhoto;
        this.PrixInitial = PrixInitial;
        this.PrixPromo = PrixPromo;
        this.NbplaceTotal = NbplaceTotal;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaceDispo = NbPlaceDispo;
        this.statut = statut;
        this.NbJaime = NbJaime;
        this.NbJaimePas = NbJaimePas;
        this.Note = Note;
    }

    public int getIdPlan() {
        return IdPlan;
    }

    public void setIdPlan(int IdPlan) {
        this.IdPlan = IdPlan;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getUrlPhoto() {
        return UrlPhoto;
    }

    public void setUrlPhoto(String UrlPhoto) {
        this.UrlPhoto = UrlPhoto;
    }

    public Double getPrixInitial() {
        return PrixInitial;
    }

    public void setPrixInitial(Double PrixInitial) {
        this.PrixInitial = PrixInitial;
    }

    public Double getPrixPromo() {
        return PrixPromo;
    }

    public void setPrixPromo(Double PrixPromo) {
        this.PrixPromo = PrixPromo;
    }

    public int getNbplaceTotal() {
        return NbplaceTotal;
    }

    public void setNbplaceTotal(int NbplaceTotal) {
        this.NbplaceTotal = NbplaceTotal;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public int getNbPlaceDispo() {
        return NbPlaceDispo;
    }

    public void setNbPlaceDispo(int NbPlaceDispo) {
        this.NbPlaceDispo = NbPlaceDispo;
    }

    public EnumValidation getStatut() {
        return statut;
    }

    public void setStatut(EnumValidation statut) {
        this.statut = statut;
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

    public int getNote() {
        return Note;
    }

    public void setNote(int Note) {
        this.Note = Note;
    }

    public int getIdAnnonceur() {
        return IdAnnonceur;
    }

    public void setIdAnnonceur(int IdAnnonceur) {
        this.IdAnnonceur = IdAnnonceur;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    
    
    
}
