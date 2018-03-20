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
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;

/**
 *
 * @author SadfiAmine
 */
public class CategorieImpl implements ICategorie{
    static  Connection con;
    private static  Statement ste;
  

    @Override
    public Categorie save(Categorie Categorie) {
          con=DataSource.getInstance().getConnection();
        try {
            ste=con.createStatement();
            String requete = "INSERT INTO categorie (Texte,UrlPhoto) VALUES('"+Categorie.getTitre()+"','"+Categorie.getUrlPhoto()+")";
            int nbMaj = ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Categorie;
    }

    @Override
    public Categorie update(Categorie Categorie) {
            con=DataSource.getInstance().getConnection();
        try {
            ste=con.createStatement();
             String requete = "UPDATE categorie SET Titre ="+Categorie.getTitre()+",UrlPhoto ="+Categorie.getUrlPhoto()+"WHERE IdCategorie="+Categorie.getIdCategorie();
            int nbMaj = ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Categorie;
    }

    @Override
    public List<Categorie> selectAll() {
              con=DataSource.getInstance().getConnection();
             List<Categorie> lc=new ArrayList();
        try {
            String requete = "SELECT * FROM categorie";
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Categorie c=new Categorie();
                c.setIdCategorie(s.getInt(1));
                c.setTitre(s.getString(2));
                c.setUrlPhoto(s.getString(3));
                lc.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }

    @Override
    public Categorie GetByID(int id) {
              con=DataSource.getInstance().getConnection();
              String requete = "select * from Categorie where IdCategorie ="+id;
             Categorie c= new Categorie();
        try {
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
             while(s.next())
            {
                c.setIdCategorie(s.getInt(1));
                c.setTitre(s.getString(2));
                c.setUrlPhoto(s.getString(3));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public void remove(int id) {
              con=DataSource.getInstance().getConnection();
              String requete = "DELETE * FROM categorie WHERE IdCategorie = "+id;
        try {
            ste=con.createStatement();
            int nbMaj = ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> selectAll(String sortfield, String sort) {
              con=DataSource.getInstance().getConnection();
              List <Categorie> lc=new ArrayList();
        try {
            String requete="select * from categorie order by"+sortfield+" "+sort;
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Categorie c=new Categorie();
                c.setIdCategorie(s.getInt(1));
                c.setTitre(s.getString(2));
                c.setUrlPhoto(s.getString(3));
                lc.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }

    @Override
    public Categorie FindOne(String ParamName, Object ParamValue) {
              con=DataSource.getInstance().getConnection();
              Categorie c =new Categorie();
              String requete="select * from categorie where"+ParamName+"="+ParamValue;
        try {
            ste=con.createStatement();
             ResultSet s = ste.executeQuery(requete);
             while(s.next())
            {
                c.setIdCategorie(s.getInt(1));
                c.setTitre(s.getString(2));
                c.setUrlPhoto(s.getString(3));
           
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return c;
    }

  
    
}
