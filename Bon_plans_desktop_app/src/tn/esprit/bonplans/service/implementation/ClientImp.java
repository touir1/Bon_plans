/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import DataSource.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.bonplans.entity.Client;
import tn.esprit.bonplans.service.IClient;
import static tn.esprit.bonplans.service.implementation.CategorieImpl.con;

/**
 *
 * @author SadfiAmine
 */
public class ClientImp implements IClient{
     static  Connection con;
    private static  Statement ste;
    @Override
    public Client save(Client entity) {
         con=DataSource.getInstance().getConnection();
         try {
            ste=con.createStatement();
            String requete = "INSERT INTO client (Nom,Prenom,Urlphoto,Ville,Adresse,Email,Mdp) VALUES('"+entity.getNom()+"','"+entity.getPrenom()+"','"+entity.getUrlphoto()+
                    "','"+entity.getVille()+"','"+entity.getAdresse()+"','"+entity.getEmail()+"','"+entity.getMdp()+")";
            int nbMaj = ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return entity;
    }

    @Override
    public Client update(Client entity) {
        con=DataSource.getInstance().getConnection();
      
         try {
             ste=con.createStatement();
             String requete = "UPDATE client SET Nom ="+entity.getNom()+",Prenom ="+entity.getPrenom()+",Urlphoto ="+entity.getUrlphoto()+",Ville ="+entity.getVille()+",Adresse ="+entity.getAdresse()+",Email ="+entity.getEmail()+",Mdp ="+entity.getMdp()+"WHERE idClient="+entity.getIdClient();
             int nbMaj = ste.executeUpdate(requete);
         } catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
           return entity;
    }

    @Override
    public List<Client> selectAll() {
          con=DataSource.getInstance().getConnection();
          List <Client> lc=new ArrayList();
         try {
              String requete = "SELECT * FROM client";
             ste=con.createStatement();
             ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Client c=new Client();
                c.setIdClient(s.getInt(1));
                c.setNom(s.getString(2));
                c.setPrenom(s.getString(3));
                c.setUrlphoto(s.getString(4));
                c.setVille(s.getString(5));
                c.setAdresse(s.getString(6));
                c.setEmail(s.getString(7));
                c.setMdp(s.getString(8));
                lc.add(c);
            }
         } catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
         return lc;
    }

    @Override
    public Client GetByID(int id) {
        con=DataSource.getInstance().getConnection();
              String requete = "select * from client where idClient ="+id;
              Client c =new Client();
         try {
             ste=con.createStatement();
              ResultSet s = ste.executeQuery(requete);
               while(s.next())
            {
               
                c.setIdClient(s.getInt(1));
                c.setNom(s.getString(2));
                c.setPrenom(s.getString(3));
                c.setUrlphoto(s.getString(4));
                c.setVille(s.getString(5));
                c.setAdresse(s.getString(6));
                c.setEmail(s.getString(7));
                c.setMdp(s.getString(8));
               
            }
              
         } catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
            return c;
    }

    @Override
    public void remove(int id) {
          con=DataSource.getInstance().getConnection();
              String requete = "DELETE * FROM client WHERE idClient = "+id;
        try {
            ste=con.createStatement();
            int nbMaj = ste.executeUpdate(requete);
    }    catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public List<Client> selectAll(String sortfield, String sort) {
          con=DataSource.getInstance().getConnection();
              List <Client> lc=new ArrayList();
        try {
            String requete="select * from client order by"+sortfield+" "+sort;
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Client c=new Client();
                c.setIdClient(s.getInt(1));
                c.setNom(s.getString(2));
                c.setPrenom(s.getString(3));
                c.setUrlphoto(s.getString(4));
                c.setVille(s.getString(5));
                c.setAdresse(s.getString(6));
                c.setEmail(s.getString(7));
                c.setMdp(s.getString(8));
                lc.add(c);
            }
    }    catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
        return lc;
    }
    @Override
    public Client FindOne(String ParamName, Object ParamValue) {
         con=DataSource.getInstance().getConnection();
              Client c =new Client();
              String requete="select * from client where"+ParamName+"="+ParamValue;
        try {
            ste=con.createStatement();
             ResultSet s = ste.executeQuery(requete);
    }    catch (SQLException ex) {
             Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
         }
        return c;
    }
    
}
