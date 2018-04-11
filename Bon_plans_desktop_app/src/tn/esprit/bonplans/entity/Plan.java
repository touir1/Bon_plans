/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

import utils.entity.EnumValidation;
import java.util.Date;

/**
 *
 * @author SadfiAmine
 */

public class Plan {
    private int idPlan;
    private String titre ;
    private String description;
    private  String urlPhoto;
    private  Double prixInitial;
    private Double prixPromo;
    private int nbPlaceTotal;
    private Date dateDebut;
    private Date dateFin;
    private int nbPlaceDispo;
    private EnumValidation statut;
    private int nbJaime ;
    private int nbJaimePas;
    private int note ;
    // 
    private int idClient;
    private int idAnnonceur;
    private int idCategorie;

    public Plan() {
    }

    public Plan(int idPlan, String titre, String description, String urlPhoto, Double prixInitial, Double prixPromo, int nbPlaceTotal, Date dateDebut, Date dateFin, int nbPlaceDispo, EnumValidation statut, int nbJaime, int nbJaimePas, int note, int idAnnonceur, int idCategorie,int idClient) {
        this.idPlan = idPlan;
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.prixInitial = prixInitial;
        this.prixPromo = prixPromo;
        this.nbPlaceTotal = nbPlaceTotal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlaceDispo = nbPlaceDispo;
        this.statut = statut;
        this.nbJaime = nbJaime;
        this.nbJaimePas = nbJaimePas;
        this.note = note;
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
        this.idClient =idClient;
    }

    public Plan(String titre, String description, String urlPhoto, Double prixInitial, Double prixPromo, int nbPlaceTotal, Date dateDebut, Date dateFin, int nbPlaceDispo, EnumValidation statut, int nbJaime, int nbJaimePas, int note, int idAnnonceur, int idCategorie, int idClient) {
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.prixInitial = prixInitial;
        this.prixPromo = prixPromo;
        this.nbPlaceTotal = nbPlaceTotal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlaceDispo = nbPlaceDispo;
        this.statut = statut;
        this.nbJaime = nbJaime;
        this.nbJaimePas = nbJaimePas;
        this.note = note;
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
        this.idClient =idClient;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(Double prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Double getPrixPromo() {
        return prixPromo;
    }

    public void setPrixPromo(Double prixPromo) {
        this.prixPromo = prixPromo;
    }

    public int getNbPlaceTotal() {
        return nbPlaceTotal;
    }

    public void setNbPlaceTotal(int nbPlaceTotal) {
        this.nbPlaceTotal = nbPlaceTotal;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }

    public EnumValidation getStatut() {
        return statut;
    }

    public void setStatut(EnumValidation statut) {
        this.statut = statut;
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdAnnonceur() {
        return idAnnonceur;
    }

    public void setIdAnnonceur(int idAnnonceur) {
        this.idAnnonceur = idAnnonceur;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Plan{" + "idPlan=" + idPlan + ", titre=" + titre + ", description=" + description + ", urlPhoto=" + urlPhoto + ", prixInitial=" + prixInitial + ", prixPromo=" + prixPromo + ", nbPlaceTotal=" + nbPlaceTotal + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nbPlaceDispo=" + nbPlaceDispo + ", statut=" + statut + ", nbJaime=" + nbJaime + ", nbJaimePas=" + nbJaimePas + ", note=" + note + ", idAnnonceur=" + idAnnonceur + ", idCategorie=" + idCategorie + '}';
    }

    
    
    
    
}
