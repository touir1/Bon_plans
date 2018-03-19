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
    private String nom;
    private String prenom;
    private String urlphoto;
    private String ville;
    private String adresse;
    private String email;
    private String mdp;

    public Client() {
    }

    public Client(int idClient, String nom, String prenom, String urlphoto, String ville, String adresse, String email, String mdp) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.urlphoto = urlphoto;
        this.ville = ville;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
    }

    public Client(String nom, String prenom, String urlphoto, String ville, String adresse, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.urlphoto = urlphoto;
        this.ville = ville;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    
    
    
    
}
