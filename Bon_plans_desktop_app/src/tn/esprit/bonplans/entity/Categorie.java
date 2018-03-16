/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

/**
 *
 * @author SadfiAmine
 */
public class Categorie {
    private int IdCategorie;
    private String Titre;
    private String UrlPhoto;

    public Categorie(int IdCategorie, String Titre, String UrlPhoto) {
        this.IdCategorie = IdCategorie;
        this.Titre = Titre;
        this.UrlPhoto = UrlPhoto;
    }

    public Categorie(String Titre, String UrlPhoto) {
        this.Titre = Titre;
        this.UrlPhoto = UrlPhoto;
    }

    public Categorie() {
    }

    
    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getUrlPhoto() {
        return UrlPhoto;
    }

    public void setUrlPhoto(String UrlPhoto) {
        this.UrlPhoto = UrlPhoto;
    }
    
    
    
}
