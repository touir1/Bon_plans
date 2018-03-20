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
    private int idCategorie;
    private String titre;
    private String urlPhoto;

    public Categorie() {
    }

    public Categorie(int idCategorie, String titre, String urlPhoto) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.urlPhoto = urlPhoto;
    }

    public Categorie(String titre, String urlPhoto) {
        this.titre = titre;
        this.urlPhoto = urlPhoto;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    
    
    
}
