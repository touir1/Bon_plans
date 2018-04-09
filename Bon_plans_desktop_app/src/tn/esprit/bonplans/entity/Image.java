/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

import java.sql.Timestamp;

/**
 *
 * @author touir
 */
public class Image {
    private int idImage;
    private String nomImage;
    private byte[] imageBytes;
    private Timestamp timestamp;

    public Image() {
    }

    public Image(String nomImage, byte[] imageBytes) {
        this.nomImage = nomImage;
        this.imageBytes = imageBytes;
    }

    public Image(int idImage, String nomImage, byte[] imageBytes, Timestamp timestamp) {
        this.idImage = idImage;
        this.nomImage = nomImage;
        this.imageBytes = imageBytes;
        this.timestamp = timestamp;
    }

    public Image(String nomImage, byte[] imageBytes, Timestamp timestamp) {
        this.nomImage = nomImage;
        this.imageBytes = imageBytes;
        this.timestamp = timestamp;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Image{" + "idImage=" + idImage + ", nomImage=" + nomImage + ", timestamp=" + timestamp + '}';
    }
    
    
}
