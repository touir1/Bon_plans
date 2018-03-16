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
public class Client {
    private int idClient;
    private String Nom;
    private String Prenom;
    private String Urlphoto;
    private String Ville;
    private String Adresse;
    private String Email;
    private String Mdp;

    public Client(int id, String Nom, String Prenom, String Urlphoto, String Ville, String Adresse, String Email, String Mdp) {
        this.idClient = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Urlphoto = Urlphoto;
        this.Ville = Ville;
        this.Adresse = Adresse;
        this.Email = Email;
        this.Mdp = Mdp;
    }

    public Client(String Nom, String Prenom, String Urlphoto, String Ville, String Adresse, String Email, String Mdp) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Urlphoto = Urlphoto;
        this.Ville = Ville;
        this.Adresse = Adresse;
        this.Email = Email;
        this.Mdp = Mdp;
    }

    public Client() {
    }
//getters and setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int id) {
        this.idClient = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getUrlphoto() {
        return Urlphoto;
    }

    public void setUrlphoto(String Urlphoto) {
        this.Urlphoto = Urlphoto;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }
    
    
    
}
