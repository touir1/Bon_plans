/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.entities;

import java.util.Date;

/**
 *
 * @author Mohamed Ali
 */
public class Plan {
    
    private Integer idPlan;
    private String titre;
    private String description;
    private String urlPhoto;
    private double prix;
    private double prixPromo;
    private int quantite;
    private Date dateDebut;
    private Date dateFin;
    private Integer quantiteDisponible;
    private int statut;
    private int idAnnonceur;
    private int idCategorie;
    private int like;
    private int dislike;

    public Plan() {
    }

    public Plan(String titre, String description, String urlPhoto, double prix, double prixPromo, int quantite, Date dateDebut, Date dateFin, int quantiteDisponible, int statut, int idAnnonceur, int idCategorie) {
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.prix = prix;
        this.prixPromo = prixPromo;
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.quantiteDisponible = quantiteDisponible;
        this.statut = statut;
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
        this.like = 0;
        this.dislike = 0;
    }

    public Plan(int idPlan, String titre, String description, String urlPhoto, double prix, double prixPromo, int quantite, Date dateDebut, Date dateFin, int quantiteDisponible, int statut, int idAnnonceur, int idCategorie) {
        this.idPlan = idPlan;
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.prix = prix;
        this.prixPromo = prixPromo;
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.quantiteDisponible = quantiteDisponible;
        this.statut = statut;
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
        this.like = 0;
        this.dislike = 0;
    }
    
    public Plan(int idPlan, String titre, String description, String urlPhoto, double prix, double prixPromo, int quantite, Date dateDebut, Date dateFin, int quantiteDisponible, int statut, int idAnnonceur, int idCategorie, int like, int dislike) {
        this.idPlan = idPlan;
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.prix = prix;
        this.prixPromo = prixPromo;
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.quantiteDisponible = quantiteDisponible;
        this.statut = statut;
        this.idAnnonceur = idAnnonceur;
        this.idCategorie = idCategorie;
        this.like = like;
        this.dislike = dislike;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrixPromo() {
        return prixPromo;
    }

    public void setPrixPromo(double prixPromo) {
        this.prixPromo = prixPromo;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
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
        return "Plan{" + "idPlan=" + idPlan + ", titre=" + titre + ", description=" + description + ", urlPhoto=" + urlPhoto + ", prix=" + prix + ", prixPromo=" + prixPromo + ", quantite=" + quantite + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", quantiteDisponible=" + quantiteDisponible + ", statut=" + statut + ", idAnnonceur=" + idAnnonceur + ", idCategorie=" + idCategorie + ", like=" + like + ", dislike=" + dislike + '}';
    }
    
    

    
        
}
