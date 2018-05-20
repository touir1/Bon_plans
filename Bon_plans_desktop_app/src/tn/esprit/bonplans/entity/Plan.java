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
    private int statut;
    private int like;
    private int dislike;
    
    //foreign keys
    private int idAnnonceur;
    private int idCategorie;

    public Plan() {
    }

    public Plan(int idPlan, String titre, String description, String urlPhoto, Double prixInitial, Double prixPromo, int nbPlaceTotal, Date dateDebut, Date dateFin, int nbPlaceDispo, int statut, int idAnnonceur, int idCategorie) {
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
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
    }

    public Plan(String titre, String description, String urlPhoto, Double prixInitial, Double prixPromo, int nbPlaceTotal, Date dateDebut, Date dateFin, int nbPlaceDispo, int statut, int idAnnonceur, int idCategorie) {
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
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
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

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        
        this.statut = statut;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return titre;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPlan;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plan other = (Plan) obj;
        if (this.idPlan != other.idPlan) {
            return false;
        }
        return true;
    }

    
    
}
