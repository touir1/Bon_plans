/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.entities;

/**
 *
 * @author Mohamed Ali
 */
public class Statistique {
    
    private int id;
    private String libelle;
    private int nombre;
    private double pourcentage;

    public Statistique() {
    }

    public Statistique(String libelle, int nombre) {
        this.libelle = libelle;
        this.nombre = nombre;
    }
    
    public Statistique(int id, String libelle, int nombre, double pourcentage) {
        this.id = id;
        this.libelle = libelle;
        this.nombre = nombre;
        this.pourcentage = pourcentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    public void afficher(){
        System.out.println("ID : " + this.id + " | Libelle : " + this.libelle + " | Nombres : " + " | Pourcentage : " + this.pourcentage);
    }
    
}
