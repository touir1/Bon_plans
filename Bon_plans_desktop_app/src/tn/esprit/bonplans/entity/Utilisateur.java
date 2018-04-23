/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author KC
 */
public class Utilisateur {
    
    private int idUtilisateur;
    private int idGroupe;
    private String mdp;
    private String email;
    private String nom;
    private String prenom;
    private String urlphoto;
    private String ville;
    private String adresse;
    private Date dateCreation;
    private Date dateAcces;
    private Timestamp tempsAcces;
    private boolean isActif;
    private int codeActivation;

    public Utilisateur() {
    }
    
    public Utilisateur(int idUtilisateur, int idGroupe, String mdp, String email, String nom, String prenom, String urlphoto, String ville, String adresse, Date dateCreation, Date dateAcces, Timestamp tempsAcces, boolean isActif, int codeActivation) {
        this.idUtilisateur = idUtilisateur;
        this.idGroupe = idGroupe;
        this.mdp = mdp;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.urlphoto = urlphoto;
        this.ville = ville;
        this.adresse = adresse;
        this.dateCreation = dateCreation;
        this.dateAcces = dateAcces;
        this.tempsAcces = tempsAcces;
        this.isActif = isActif;
        this.codeActivation = codeActivation;
    }
    
    public Utilisateur (int idGroupe, String mdp, String email, String nom, String prenom, Date dateCreation, int codeActivation){
        this.idGroupe = idGroupe;
        this.mdp = mdp;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateCreation;
        this.isActif = false;
        this.codeActivation = codeActivation;
    }

    public int getCodeActivation() {
        return codeActivation;
    }

    public void setCodeActivation(int codeActivation) {
        this.codeActivation = codeActivation;
    }

    public boolean getIsActif() {
        return isActif;
    }

    public void setIsActif(boolean isActif) {
        this.isActif = isActif;
    }
    
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateAcces() {
        return dateAcces;
    }

    public void setDateAcces(Date dateAcces) {
        this.dateAcces = dateAcces;
    }

    public Timestamp getTempsAcces() {
        return tempsAcces;
    }

    public void setTempsAcces(Timestamp tempsAcces) {
        this.tempsAcces = tempsAcces;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idUtilisateur=" + idUtilisateur + ", idGroupe=" + idGroupe + ", mdp=" + mdp + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", urlphoto=" + urlphoto + ", ville=" + ville + ", adresse=" + adresse + ", dateCreation=" + dateCreation + ", dateAcces=" + dateAcces + ", tempsAcces=" + tempsAcces + ", isActif=" + isActif + ", codeActivation=" + codeActivation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idUtilisateur;
        hash = 17 * hash + Objects.hashCode(this.email);
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }    

}
