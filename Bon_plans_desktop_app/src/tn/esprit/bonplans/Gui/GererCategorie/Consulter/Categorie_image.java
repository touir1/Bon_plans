/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererCategorie.Consulter;

import javafx.scene.image.ImageView;



/**
 *
 * @author SadfiAmine
 */
public class Categorie_image {
    private int idCategorie;
    private String titre;
    private ImageView photo;

    public Categorie_image(int idCategorie, String titre, ImageView photo) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.photo = photo;
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

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    
}
